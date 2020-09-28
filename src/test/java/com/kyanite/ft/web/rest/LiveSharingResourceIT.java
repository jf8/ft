package com.kyanite.ft.web.rest;

import com.kyanite.ft.RedisTestContainerExtension;
import com.kyanite.ft.FtApp;
import com.kyanite.ft.domain.LiveSharing;
import com.kyanite.ft.repository.LiveSharingRepository;
import com.kyanite.ft.service.LiveSharingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LiveSharingResource} REST controller.
 */
@SpringBootTest(classes = FtApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class LiveSharingResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LiveSharingRepository liveSharingRepository;

    @Autowired
    private LiveSharingService liveSharingService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLiveSharingMockMvc;

    private LiveSharing liveSharing;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LiveSharing createEntity(EntityManager em) {
        LiveSharing liveSharing = new LiveSharing()
            .name(DEFAULT_NAME)
            .day(DEFAULT_DAY);
        return liveSharing;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LiveSharing createUpdatedEntity(EntityManager em) {
        LiveSharing liveSharing = new LiveSharing()
            .name(UPDATED_NAME)
            .day(UPDATED_DAY);
        return liveSharing;
    }

    @BeforeEach
    public void initTest() {
        liveSharing = createEntity(em);
    }

    @Test
    @Transactional
    public void createLiveSharing() throws Exception {
        int databaseSizeBeforeCreate = liveSharingRepository.findAll().size();
        // Create the LiveSharing
        restLiveSharingMockMvc.perform(post("/api/live-sharings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(liveSharing)))
            .andExpect(status().isCreated());

        // Validate the LiveSharing in the database
        List<LiveSharing> liveSharingList = liveSharingRepository.findAll();
        assertThat(liveSharingList).hasSize(databaseSizeBeforeCreate + 1);
        LiveSharing testLiveSharing = liveSharingList.get(liveSharingList.size() - 1);
        assertThat(testLiveSharing.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLiveSharing.getDay()).isEqualTo(DEFAULT_DAY);
    }

    @Test
    @Transactional
    public void createLiveSharingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = liveSharingRepository.findAll().size();

        // Create the LiveSharing with an existing ID
        liveSharing.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLiveSharingMockMvc.perform(post("/api/live-sharings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(liveSharing)))
            .andExpect(status().isBadRequest());

        // Validate the LiveSharing in the database
        List<LiveSharing> liveSharingList = liveSharingRepository.findAll();
        assertThat(liveSharingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLiveSharings() throws Exception {
        // Initialize the database
        liveSharingRepository.saveAndFlush(liveSharing);

        // Get all the liveSharingList
        restLiveSharingMockMvc.perform(get("/api/live-sharings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(liveSharing.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())));
    }
    
    @Test
    @Transactional
    public void getLiveSharing() throws Exception {
        // Initialize the database
        liveSharingRepository.saveAndFlush(liveSharing);

        // Get the liveSharing
        restLiveSharingMockMvc.perform(get("/api/live-sharings/{id}", liveSharing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(liveSharing.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingLiveSharing() throws Exception {
        // Get the liveSharing
        restLiveSharingMockMvc.perform(get("/api/live-sharings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLiveSharing() throws Exception {
        // Initialize the database
        liveSharingService.save(liveSharing);

        int databaseSizeBeforeUpdate = liveSharingRepository.findAll().size();

        // Update the liveSharing
        LiveSharing updatedLiveSharing = liveSharingRepository.findById(liveSharing.getId()).get();
        // Disconnect from session so that the updates on updatedLiveSharing are not directly saved in db
        em.detach(updatedLiveSharing);
        updatedLiveSharing
            .name(UPDATED_NAME)
            .day(UPDATED_DAY);

        restLiveSharingMockMvc.perform(put("/api/live-sharings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLiveSharing)))
            .andExpect(status().isOk());

        // Validate the LiveSharing in the database
        List<LiveSharing> liveSharingList = liveSharingRepository.findAll();
        assertThat(liveSharingList).hasSize(databaseSizeBeforeUpdate);
        LiveSharing testLiveSharing = liveSharingList.get(liveSharingList.size() - 1);
        assertThat(testLiveSharing.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLiveSharing.getDay()).isEqualTo(UPDATED_DAY);
    }

    @Test
    @Transactional
    public void updateNonExistingLiveSharing() throws Exception {
        int databaseSizeBeforeUpdate = liveSharingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLiveSharingMockMvc.perform(put("/api/live-sharings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(liveSharing)))
            .andExpect(status().isBadRequest());

        // Validate the LiveSharing in the database
        List<LiveSharing> liveSharingList = liveSharingRepository.findAll();
        assertThat(liveSharingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLiveSharing() throws Exception {
        // Initialize the database
        liveSharingService.save(liveSharing);

        int databaseSizeBeforeDelete = liveSharingRepository.findAll().size();

        // Delete the liveSharing
        restLiveSharingMockMvc.perform(delete("/api/live-sharings/{id}", liveSharing.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LiveSharing> liveSharingList = liveSharingRepository.findAll();
        assertThat(liveSharingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

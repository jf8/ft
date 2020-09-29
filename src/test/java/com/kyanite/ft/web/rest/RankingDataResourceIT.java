package com.kyanite.ft.web.rest;

import com.kyanite.ft.RedisTestContainerExtension;
import com.kyanite.ft.FtApp;
import com.kyanite.ft.domain.RankingData;
import com.kyanite.ft.repository.RankingDataRepository;
import com.kyanite.ft.service.RankingDataService;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RankingDataResource} REST controller.
 */
@SpringBootTest(classes = FtApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class RankingDataResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_TOTAL_PEOPLE = 1L;
    private static final Long UPDATED_TOTAL_PEOPLE = 2L;

    private static final Long DEFAULT_SIGND_PEOPLE = 1L;
    private static final Long UPDATED_SIGND_PEOPLE = 2L;

    private static final BigDecimal DEFAULT_ATTENDANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ATTENDANCE = new BigDecimal(2);

    private static final Long DEFAULT_ORDER_NUM = 1L;
    private static final Long UPDATED_ORDER_NUM = 2L;

    private static final Long DEFAULT_PARENT_ID = 1L;
    private static final Long UPDATED_PARENT_ID = 2L;

    private static final Instant DEFAULT_DAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_LEAF = false;
    private static final Boolean UPDATED_IS_LEAF = true;

    @Autowired
    private RankingDataRepository rankingDataRepository;

    @Autowired
    private RankingDataService rankingDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRankingDataMockMvc;

    private RankingData rankingData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankingData createEntity(EntityManager em) {
        RankingData rankingData = new RankingData()
            .name(DEFAULT_NAME)
            .totalPeople(DEFAULT_TOTAL_PEOPLE)
            .signdPeople(DEFAULT_SIGND_PEOPLE)
            .attendance(DEFAULT_ATTENDANCE)
            .orderNum(DEFAULT_ORDER_NUM)
            .parentId(DEFAULT_PARENT_ID)
            .day(DEFAULT_DAY)
            .isLeaf(DEFAULT_IS_LEAF);
        return rankingData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankingData createUpdatedEntity(EntityManager em) {
        RankingData rankingData = new RankingData()
            .name(UPDATED_NAME)
            .totalPeople(UPDATED_TOTAL_PEOPLE)
            .signdPeople(UPDATED_SIGND_PEOPLE)
            .attendance(UPDATED_ATTENDANCE)
            .orderNum(UPDATED_ORDER_NUM)
            .parentId(UPDATED_PARENT_ID)
            .day(UPDATED_DAY)
            .isLeaf(UPDATED_IS_LEAF);
        return rankingData;
    }

    @BeforeEach
    public void initTest() {
        rankingData = createEntity(em);
    }

    @Test
    @Transactional
    public void createRankingData() throws Exception {
        int databaseSizeBeforeCreate = rankingDataRepository.findAll().size();
        // Create the RankingData
        restRankingDataMockMvc.perform(post("/api/ranking-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rankingData)))
            .andExpect(status().isCreated());

        // Validate the RankingData in the database
        List<RankingData> rankingDataList = rankingDataRepository.findAll();
        assertThat(rankingDataList).hasSize(databaseSizeBeforeCreate + 1);
        RankingData testRankingData = rankingDataList.get(rankingDataList.size() - 1);
        assertThat(testRankingData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRankingData.getTotalPeople()).isEqualTo(DEFAULT_TOTAL_PEOPLE);
        assertThat(testRankingData.getSigndPeople()).isEqualTo(DEFAULT_SIGND_PEOPLE);
        assertThat(testRankingData.getAttendance()).isEqualTo(DEFAULT_ATTENDANCE);
        assertThat(testRankingData.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testRankingData.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testRankingData.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testRankingData.isIsLeaf()).isEqualTo(DEFAULT_IS_LEAF);
    }

    @Test
    @Transactional
    public void createRankingDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rankingDataRepository.findAll().size();

        // Create the RankingData with an existing ID
        rankingData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRankingDataMockMvc.perform(post("/api/ranking-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rankingData)))
            .andExpect(status().isBadRequest());

        // Validate the RankingData in the database
        List<RankingData> rankingDataList = rankingDataRepository.findAll();
        assertThat(rankingDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRankingData() throws Exception {
        // Initialize the database
        rankingDataRepository.saveAndFlush(rankingData);

        // Get all the rankingDataList
        restRankingDataMockMvc.perform(get("/api/ranking-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rankingData.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].totalPeople").value(hasItem(DEFAULT_TOTAL_PEOPLE.intValue())))
            .andExpect(jsonPath("$.[*].signdPeople").value(hasItem(DEFAULT_SIGND_PEOPLE.intValue())))
            .andExpect(jsonPath("$.[*].attendance").value(hasItem(DEFAULT_ATTENDANCE.intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM.intValue())))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
            .andExpect(jsonPath("$.[*].isLeaf").value(hasItem(DEFAULT_IS_LEAF.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getRankingData() throws Exception {
        // Initialize the database
        rankingDataRepository.saveAndFlush(rankingData);

        // Get the rankingData
        restRankingDataMockMvc.perform(get("/api/ranking-data/{id}", rankingData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rankingData.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.totalPeople").value(DEFAULT_TOTAL_PEOPLE.intValue()))
            .andExpect(jsonPath("$.signdPeople").value(DEFAULT_SIGND_PEOPLE.intValue()))
            .andExpect(jsonPath("$.attendance").value(DEFAULT_ATTENDANCE.intValue()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM.intValue()))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID.intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()))
            .andExpect(jsonPath("$.isLeaf").value(DEFAULT_IS_LEAF.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingRankingData() throws Exception {
        // Get the rankingData
        restRankingDataMockMvc.perform(get("/api/ranking-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRankingData() throws Exception {
        // Initialize the database
        rankingDataService.save(rankingData);

        int databaseSizeBeforeUpdate = rankingDataRepository.findAll().size();

        // Update the rankingData
        RankingData updatedRankingData = rankingDataRepository.findById(rankingData.getId()).get();
        // Disconnect from session so that the updates on updatedRankingData are not directly saved in db
        em.detach(updatedRankingData);
        updatedRankingData
            .name(UPDATED_NAME)
            .totalPeople(UPDATED_TOTAL_PEOPLE)
            .signdPeople(UPDATED_SIGND_PEOPLE)
            .attendance(UPDATED_ATTENDANCE)
            .orderNum(UPDATED_ORDER_NUM)
            .parentId(UPDATED_PARENT_ID)
            .day(UPDATED_DAY)
            .isLeaf(UPDATED_IS_LEAF);

        restRankingDataMockMvc.perform(put("/api/ranking-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRankingData)))
            .andExpect(status().isOk());

        // Validate the RankingData in the database
        List<RankingData> rankingDataList = rankingDataRepository.findAll();
        assertThat(rankingDataList).hasSize(databaseSizeBeforeUpdate);
        RankingData testRankingData = rankingDataList.get(rankingDataList.size() - 1);
        assertThat(testRankingData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRankingData.getTotalPeople()).isEqualTo(UPDATED_TOTAL_PEOPLE);
        assertThat(testRankingData.getSigndPeople()).isEqualTo(UPDATED_SIGND_PEOPLE);
        assertThat(testRankingData.getAttendance()).isEqualTo(UPDATED_ATTENDANCE);
        assertThat(testRankingData.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testRankingData.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testRankingData.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testRankingData.isIsLeaf()).isEqualTo(UPDATED_IS_LEAF);
    }

    @Test
    @Transactional
    public void updateNonExistingRankingData() throws Exception {
        int databaseSizeBeforeUpdate = rankingDataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankingDataMockMvc.perform(put("/api/ranking-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rankingData)))
            .andExpect(status().isBadRequest());

        // Validate the RankingData in the database
        List<RankingData> rankingDataList = rankingDataRepository.findAll();
        assertThat(rankingDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRankingData() throws Exception {
        // Initialize the database
        rankingDataService.save(rankingData);

        int databaseSizeBeforeDelete = rankingDataRepository.findAll().size();

        // Delete the rankingData
        restRankingDataMockMvc.perform(delete("/api/ranking-data/{id}", rankingData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RankingData> rankingDataList = rankingDataRepository.findAll();
        assertThat(rankingDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

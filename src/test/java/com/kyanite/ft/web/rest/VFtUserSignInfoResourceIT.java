package com.kyanite.ft.web.rest;

import com.kyanite.ft.FtApp;
import com.kyanite.ft.domain.VFtUserSignInfo;
import com.kyanite.ft.repository.VFtUserSignInfoRepository;
import com.kyanite.ft.service.VFtUserSignInfoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
 * Integration tests for the {@link VFtUserSignInfoResource} REST controller.
 */
@SpringBootTest(classes = FtApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VFtUserSignInfoResourceIT {

    private static final String DEFAULT_PHONE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SEAT = "AAAAAAAAAA";
    private static final String UPDATED_SEAT = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_IDS = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_IDS = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NAME_CN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_CN = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_CN = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_EN = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_EN = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE_CN = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_CN = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE_EN = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_EN = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_DDID = "AAAAAAAAAA";
    private static final String UPDATED_DDID = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SIGN_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SIGN_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_MEET_ID = 1L;
    private static final Long UPDATED_MEET_ID = 2L;

    @Autowired
    private VFtUserSignInfoRepository vFtUserSignInfoRepository;

    @Autowired
    private VFtUserSignInfoService vFtUserSignInfoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVFtUserSignInfoMockMvc;

    private VFtUserSignInfo vFtUserSignInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VFtUserSignInfo createEntity(EntityManager em) {
        VFtUserSignInfo vFtUserSignInfo = new VFtUserSignInfo()
            .phoneCode(DEFAULT_PHONE_CODE)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .seat(DEFAULT_SEAT)
            .groupIds(DEFAULT_GROUP_IDS)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .nameCn(DEFAULT_NAME_CN)
            .nameEn(DEFAULT_NAME_EN)
            .companyCn(DEFAULT_COMPANY_CN)
            .companyEn(DEFAULT_COMPANY_EN)
            .titleCn(DEFAULT_TITLE_CN)
            .titleEn(DEFAULT_TITLE_EN)
            .remark(DEFAULT_REMARK)
            .ddid(DEFAULT_DDID)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createTime(DEFAULT_CREATE_TIME)
            .signTime(DEFAULT_SIGN_TIME)
            .meetId(DEFAULT_MEET_ID);
        return vFtUserSignInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VFtUserSignInfo createUpdatedEntity(EntityManager em) {
        VFtUserSignInfo vFtUserSignInfo = new VFtUserSignInfo()
            .phoneCode(UPDATED_PHONE_CODE)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .seat(UPDATED_SEAT)
            .groupIds(UPDATED_GROUP_IDS)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .nameCn(UPDATED_NAME_CN)
            .nameEn(UPDATED_NAME_EN)
            .companyCn(UPDATED_COMPANY_CN)
            .companyEn(UPDATED_COMPANY_EN)
            .titleCn(UPDATED_TITLE_CN)
            .titleEn(UPDATED_TITLE_EN)
            .remark(UPDATED_REMARK)
            .ddid(UPDATED_DDID)
            .updateTime(UPDATED_UPDATE_TIME)
            .createTime(UPDATED_CREATE_TIME)
            .signTime(UPDATED_SIGN_TIME)
            .meetId(UPDATED_MEET_ID);
        return vFtUserSignInfo;
    }

    @BeforeEach
    public void initTest() {
        vFtUserSignInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createVFtUserSignInfo() throws Exception {
        int databaseSizeBeforeCreate = vFtUserSignInfoRepository.findAll().size();
        // Create the VFtUserSignInfo
        restVFtUserSignInfoMockMvc.perform(post("/api/v-ft-user-sign-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vFtUserSignInfo)))
            .andExpect(status().isCreated());

        // Validate the VFtUserSignInfo in the database
        List<VFtUserSignInfo> vFtUserSignInfoList = vFtUserSignInfoRepository.findAll();
        assertThat(vFtUserSignInfoList).hasSize(databaseSizeBeforeCreate + 1);
        VFtUserSignInfo testVFtUserSignInfo = vFtUserSignInfoList.get(vFtUserSignInfoList.size() - 1);
        assertThat(testVFtUserSignInfo.getPhoneCode()).isEqualTo(DEFAULT_PHONE_CODE);
        assertThat(testVFtUserSignInfo.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testVFtUserSignInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testVFtUserSignInfo.getSeat()).isEqualTo(DEFAULT_SEAT);
        assertThat(testVFtUserSignInfo.getGroupIds()).isEqualTo(DEFAULT_GROUP_IDS);
        assertThat(testVFtUserSignInfo.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testVFtUserSignInfo.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testVFtUserSignInfo.getNameCn()).isEqualTo(DEFAULT_NAME_CN);
        assertThat(testVFtUserSignInfo.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testVFtUserSignInfo.getCompanyCn()).isEqualTo(DEFAULT_COMPANY_CN);
        assertThat(testVFtUserSignInfo.getCompanyEn()).isEqualTo(DEFAULT_COMPANY_EN);
        assertThat(testVFtUserSignInfo.getTitleCn()).isEqualTo(DEFAULT_TITLE_CN);
        assertThat(testVFtUserSignInfo.getTitleEn()).isEqualTo(DEFAULT_TITLE_EN);
        assertThat(testVFtUserSignInfo.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testVFtUserSignInfo.getDdid()).isEqualTo(DEFAULT_DDID);
        assertThat(testVFtUserSignInfo.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testVFtUserSignInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testVFtUserSignInfo.getSignTime()).isEqualTo(DEFAULT_SIGN_TIME);
        assertThat(testVFtUserSignInfo.getMeetId()).isEqualTo(DEFAULT_MEET_ID);
    }

    @Test
    @Transactional
    public void createVFtUserSignInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vFtUserSignInfoRepository.findAll().size();

        // Create the VFtUserSignInfo with an existing ID
        vFtUserSignInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVFtUserSignInfoMockMvc.perform(post("/api/v-ft-user-sign-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vFtUserSignInfo)))
            .andExpect(status().isBadRequest());

        // Validate the VFtUserSignInfo in the database
        List<VFtUserSignInfo> vFtUserSignInfoList = vFtUserSignInfoRepository.findAll();
        assertThat(vFtUserSignInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVFtUserSignInfos() throws Exception {
        // Initialize the database
        vFtUserSignInfoRepository.saveAndFlush(vFtUserSignInfo);

        // Get all the vFtUserSignInfoList
        restVFtUserSignInfoMockMvc.perform(get("/api/v-ft-user-sign-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vFtUserSignInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].phoneCode").value(hasItem(DEFAULT_PHONE_CODE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].seat").value(hasItem(DEFAULT_SEAT)))
            .andExpect(jsonPath("$.[*].groupIds").value(hasItem(DEFAULT_GROUP_IDS)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].nameCn").value(hasItem(DEFAULT_NAME_CN)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].companyCn").value(hasItem(DEFAULT_COMPANY_CN)))
            .andExpect(jsonPath("$.[*].companyEn").value(hasItem(DEFAULT_COMPANY_EN)))
            .andExpect(jsonPath("$.[*].titleCn").value(hasItem(DEFAULT_TITLE_CN)))
            .andExpect(jsonPath("$.[*].titleEn").value(hasItem(DEFAULT_TITLE_EN)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].ddid").value(hasItem(DEFAULT_DDID)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].signTime").value(hasItem(DEFAULT_SIGN_TIME.toString())))
            .andExpect(jsonPath("$.[*].meetId").value(hasItem(DEFAULT_MEET_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getVFtUserSignInfo() throws Exception {
        // Initialize the database
        vFtUserSignInfoRepository.saveAndFlush(vFtUserSignInfo);

        // Get the vFtUserSignInfo
        restVFtUserSignInfoMockMvc.perform(get("/api/v-ft-user-sign-infos/{id}", vFtUserSignInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vFtUserSignInfo.getId().intValue()))
            .andExpect(jsonPath("$.phoneCode").value(DEFAULT_PHONE_CODE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.seat").value(DEFAULT_SEAT))
            .andExpect(jsonPath("$.groupIds").value(DEFAULT_GROUP_IDS))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.nameCn").value(DEFAULT_NAME_CN))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.companyCn").value(DEFAULT_COMPANY_CN))
            .andExpect(jsonPath("$.companyEn").value(DEFAULT_COMPANY_EN))
            .andExpect(jsonPath("$.titleCn").value(DEFAULT_TITLE_CN))
            .andExpect(jsonPath("$.titleEn").value(DEFAULT_TITLE_EN))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.ddid").value(DEFAULT_DDID))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.signTime").value(DEFAULT_SIGN_TIME.toString()))
            .andExpect(jsonPath("$.meetId").value(DEFAULT_MEET_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingVFtUserSignInfo() throws Exception {
        // Get the vFtUserSignInfo
        restVFtUserSignInfoMockMvc.perform(get("/api/v-ft-user-sign-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVFtUserSignInfo() throws Exception {
        // Initialize the database
        vFtUserSignInfoService.save(vFtUserSignInfo);

        int databaseSizeBeforeUpdate = vFtUserSignInfoRepository.findAll().size();

        // Update the vFtUserSignInfo
        VFtUserSignInfo updatedVFtUserSignInfo = vFtUserSignInfoRepository.findById(vFtUserSignInfo.getId()).get();
        // Disconnect from session so that the updates on updatedVFtUserSignInfo are not directly saved in db
        em.detach(updatedVFtUserSignInfo);
        updatedVFtUserSignInfo
            .phoneCode(UPDATED_PHONE_CODE)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .seat(UPDATED_SEAT)
            .groupIds(UPDATED_GROUP_IDS)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .nameCn(UPDATED_NAME_CN)
            .nameEn(UPDATED_NAME_EN)
            .companyCn(UPDATED_COMPANY_CN)
            .companyEn(UPDATED_COMPANY_EN)
            .titleCn(UPDATED_TITLE_CN)
            .titleEn(UPDATED_TITLE_EN)
            .remark(UPDATED_REMARK)
            .ddid(UPDATED_DDID)
            .updateTime(UPDATED_UPDATE_TIME)
            .createTime(UPDATED_CREATE_TIME)
            .signTime(UPDATED_SIGN_TIME)
            .meetId(UPDATED_MEET_ID);

        restVFtUserSignInfoMockMvc.perform(put("/api/v-ft-user-sign-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVFtUserSignInfo)))
            .andExpect(status().isOk());

        // Validate the VFtUserSignInfo in the database
        List<VFtUserSignInfo> vFtUserSignInfoList = vFtUserSignInfoRepository.findAll();
        assertThat(vFtUserSignInfoList).hasSize(databaseSizeBeforeUpdate);
        VFtUserSignInfo testVFtUserSignInfo = vFtUserSignInfoList.get(vFtUserSignInfoList.size() - 1);
        assertThat(testVFtUserSignInfo.getPhoneCode()).isEqualTo(UPDATED_PHONE_CODE);
        assertThat(testVFtUserSignInfo.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testVFtUserSignInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVFtUserSignInfo.getSeat()).isEqualTo(UPDATED_SEAT);
        assertThat(testVFtUserSignInfo.getGroupIds()).isEqualTo(UPDATED_GROUP_IDS);
        assertThat(testVFtUserSignInfo.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testVFtUserSignInfo.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testVFtUserSignInfo.getNameCn()).isEqualTo(UPDATED_NAME_CN);
        assertThat(testVFtUserSignInfo.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testVFtUserSignInfo.getCompanyCn()).isEqualTo(UPDATED_COMPANY_CN);
        assertThat(testVFtUserSignInfo.getCompanyEn()).isEqualTo(UPDATED_COMPANY_EN);
        assertThat(testVFtUserSignInfo.getTitleCn()).isEqualTo(UPDATED_TITLE_CN);
        assertThat(testVFtUserSignInfo.getTitleEn()).isEqualTo(UPDATED_TITLE_EN);
        assertThat(testVFtUserSignInfo.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testVFtUserSignInfo.getDdid()).isEqualTo(UPDATED_DDID);
        assertThat(testVFtUserSignInfo.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testVFtUserSignInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testVFtUserSignInfo.getSignTime()).isEqualTo(UPDATED_SIGN_TIME);
        assertThat(testVFtUserSignInfo.getMeetId()).isEqualTo(UPDATED_MEET_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingVFtUserSignInfo() throws Exception {
        int databaseSizeBeforeUpdate = vFtUserSignInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVFtUserSignInfoMockMvc.perform(put("/api/v-ft-user-sign-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vFtUserSignInfo)))
            .andExpect(status().isBadRequest());

        // Validate the VFtUserSignInfo in the database
        List<VFtUserSignInfo> vFtUserSignInfoList = vFtUserSignInfoRepository.findAll();
        assertThat(vFtUserSignInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVFtUserSignInfo() throws Exception {
        // Initialize the database
        vFtUserSignInfoService.save(vFtUserSignInfo);

        int databaseSizeBeforeDelete = vFtUserSignInfoRepository.findAll().size();

        // Delete the vFtUserSignInfo
        restVFtUserSignInfoMockMvc.perform(delete("/api/v-ft-user-sign-infos/{id}", vFtUserSignInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VFtUserSignInfo> vFtUserSignInfoList = vFtUserSignInfoRepository.findAll();
        assertThat(vFtUserSignInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

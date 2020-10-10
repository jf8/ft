package com.kyanite.ft.web.rest;

import com.kyanite.ft.FtApp;
import com.kyanite.ft.domain.DdBookDept;
import com.kyanite.ft.repository.DdBookDeptRepository;
import com.kyanite.ft.service.DdBookDeptService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DdBookDeptResource} REST controller.
 */
@SpringBootTest(classes = FtApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DdBookDeptResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDER_NUM = 1L;
    private static final Long UPDATED_ORDER_NUM = 2L;

    private static final Long DEFAULT_PARENTID = 1L;
    private static final Long UPDATED_PARENTID = 2L;

    private static final Boolean DEFAULT_CREATE_DEPT_GROUP = false;
    private static final Boolean UPDATED_CREATE_DEPT_GROUP = true;

    private static final Boolean DEFAULT_AUTO_ADD_USER = false;
    private static final Boolean UPDATED_AUTO_ADD_USER = true;

    private static final Boolean DEFAULT_DEPT_HIDING = false;
    private static final Boolean UPDATED_DEPT_HIDING = true;

    private static final String DEFAULT_DEPT_PERMITS = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_PERMITS = "BBBBBBBBBB";

    private static final String DEFAULT_USER_PERMITS = "AAAAAAAAAA";
    private static final String UPDATED_USER_PERMITS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OUTER_DEPT = false;
    private static final Boolean UPDATED_OUTER_DEPT = true;

    private static final String DEFAULT_OUTER_PERMIT_DEPTS = "AAAAAAAAAA";
    private static final String UPDATED_OUTER_PERMIT_DEPTS = "BBBBBBBBBB";

    private static final String DEFAULT_OUTER_PERMIT_USERS = "AAAAAAAAAA";
    private static final String UPDATED_OUTER_PERMIT_USERS = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_DEPT_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_ORG_DEPT_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_MANAGER_USERID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_MANAGER_USERID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_EXT = "AAAAAAAAAA";
    private static final String UPDATED_EXT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_LEAF = false;
    private static final Boolean UPDATED_IS_LEAF = true;

    @Autowired
    private DdBookDeptRepository ddBookDeptRepository;

    @Autowired
    private DdBookDeptService ddBookDeptService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDdBookDeptMockMvc;

    private DdBookDept ddBookDept;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DdBookDept createEntity(EntityManager em) {
        DdBookDept ddBookDept = new DdBookDept()
            .name(DEFAULT_NAME)
            .orderNum(DEFAULT_ORDER_NUM)
            .parentid(DEFAULT_PARENTID)
            .createDeptGroup(DEFAULT_CREATE_DEPT_GROUP)
            .autoAddUser(DEFAULT_AUTO_ADD_USER)
            .deptHiding(DEFAULT_DEPT_HIDING)
            .deptPermits(DEFAULT_DEPT_PERMITS)
            .userPermits(DEFAULT_USER_PERMITS)
            .outerDept(DEFAULT_OUTER_DEPT)
            .outerPermitDepts(DEFAULT_OUTER_PERMIT_DEPTS)
            .outerPermitUsers(DEFAULT_OUTER_PERMIT_USERS)
            .orgDeptOwner(DEFAULT_ORG_DEPT_OWNER)
            .deptManagerUseridList(DEFAULT_DEPT_MANAGER_USERID_LIST)
            .sourceIdentifier(DEFAULT_SOURCE_IDENTIFIER)
            .ext(DEFAULT_EXT)
            .isLeaf(DEFAULT_IS_LEAF);
        return ddBookDept;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DdBookDept createUpdatedEntity(EntityManager em) {
        DdBookDept ddBookDept = new DdBookDept()
            .name(UPDATED_NAME)
            .orderNum(UPDATED_ORDER_NUM)
            .parentid(UPDATED_PARENTID)
            .createDeptGroup(UPDATED_CREATE_DEPT_GROUP)
            .autoAddUser(UPDATED_AUTO_ADD_USER)
            .deptHiding(UPDATED_DEPT_HIDING)
            .deptPermits(UPDATED_DEPT_PERMITS)
            .userPermits(UPDATED_USER_PERMITS)
            .outerDept(UPDATED_OUTER_DEPT)
            .outerPermitDepts(UPDATED_OUTER_PERMIT_DEPTS)
            .outerPermitUsers(UPDATED_OUTER_PERMIT_USERS)
            .orgDeptOwner(UPDATED_ORG_DEPT_OWNER)
            .deptManagerUseridList(UPDATED_DEPT_MANAGER_USERID_LIST)
            .sourceIdentifier(UPDATED_SOURCE_IDENTIFIER)
            .ext(UPDATED_EXT)
            .isLeaf(UPDATED_IS_LEAF);
        return ddBookDept;
    }

    @BeforeEach
    public void initTest() {
        ddBookDept = createEntity(em);
    }

    @Test
    @Transactional
    public void createDdBookDept() throws Exception {
        int databaseSizeBeforeCreate = ddBookDeptRepository.findAll().size();
        // Create the DdBookDept
        restDdBookDeptMockMvc.perform(post("/api/dd-book-depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddBookDept)))
            .andExpect(status().isCreated());

        // Validate the DdBookDept in the database
        List<DdBookDept> ddBookDeptList = ddBookDeptRepository.findAll();
        assertThat(ddBookDeptList).hasSize(databaseSizeBeforeCreate + 1);
        DdBookDept testDdBookDept = ddBookDeptList.get(ddBookDeptList.size() - 1);
        assertThat(testDdBookDept.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDdBookDept.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testDdBookDept.getParentid()).isEqualTo(DEFAULT_PARENTID);
        assertThat(testDdBookDept.isCreateDeptGroup()).isEqualTo(DEFAULT_CREATE_DEPT_GROUP);
        assertThat(testDdBookDept.isAutoAddUser()).isEqualTo(DEFAULT_AUTO_ADD_USER);
        assertThat(testDdBookDept.isDeptHiding()).isEqualTo(DEFAULT_DEPT_HIDING);
        assertThat(testDdBookDept.getDeptPermits()).isEqualTo(DEFAULT_DEPT_PERMITS);
        assertThat(testDdBookDept.getUserPermits()).isEqualTo(DEFAULT_USER_PERMITS);
        assertThat(testDdBookDept.isOuterDept()).isEqualTo(DEFAULT_OUTER_DEPT);
        assertThat(testDdBookDept.getOuterPermitDepts()).isEqualTo(DEFAULT_OUTER_PERMIT_DEPTS);
        assertThat(testDdBookDept.getOuterPermitUsers()).isEqualTo(DEFAULT_OUTER_PERMIT_USERS);
        assertThat(testDdBookDept.getOrgDeptOwner()).isEqualTo(DEFAULT_ORG_DEPT_OWNER);
        assertThat(testDdBookDept.getDeptManagerUseridList()).isEqualTo(DEFAULT_DEPT_MANAGER_USERID_LIST);
        assertThat(testDdBookDept.getSourceIdentifier()).isEqualTo(DEFAULT_SOURCE_IDENTIFIER);
        assertThat(testDdBookDept.getExt()).isEqualTo(DEFAULT_EXT);
        assertThat(testDdBookDept.isIsLeaf()).isEqualTo(DEFAULT_IS_LEAF);
    }

    @Test
    @Transactional
    public void createDdBookDeptWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ddBookDeptRepository.findAll().size();

        // Create the DdBookDept with an existing ID
        ddBookDept.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDdBookDeptMockMvc.perform(post("/api/dd-book-depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddBookDept)))
            .andExpect(status().isBadRequest());

        // Validate the DdBookDept in the database
        List<DdBookDept> ddBookDeptList = ddBookDeptRepository.findAll();
        assertThat(ddBookDeptList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDdBookDepts() throws Exception {
        // Initialize the database
        ddBookDeptRepository.saveAndFlush(ddBookDept);

        // Get all the ddBookDeptList
        restDdBookDeptMockMvc.perform(get("/api/dd-book-depts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ddBookDept.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM.intValue())))
            .andExpect(jsonPath("$.[*].parentid").value(hasItem(DEFAULT_PARENTID.intValue())))
            .andExpect(jsonPath("$.[*].createDeptGroup").value(hasItem(DEFAULT_CREATE_DEPT_GROUP.booleanValue())))
            .andExpect(jsonPath("$.[*].autoAddUser").value(hasItem(DEFAULT_AUTO_ADD_USER.booleanValue())))
            .andExpect(jsonPath("$.[*].deptHiding").value(hasItem(DEFAULT_DEPT_HIDING.booleanValue())))
            .andExpect(jsonPath("$.[*].deptPermits").value(hasItem(DEFAULT_DEPT_PERMITS)))
            .andExpect(jsonPath("$.[*].userPermits").value(hasItem(DEFAULT_USER_PERMITS)))
            .andExpect(jsonPath("$.[*].outerDept").value(hasItem(DEFAULT_OUTER_DEPT.booleanValue())))
            .andExpect(jsonPath("$.[*].outerPermitDepts").value(hasItem(DEFAULT_OUTER_PERMIT_DEPTS)))
            .andExpect(jsonPath("$.[*].outerPermitUsers").value(hasItem(DEFAULT_OUTER_PERMIT_USERS)))
            .andExpect(jsonPath("$.[*].orgDeptOwner").value(hasItem(DEFAULT_ORG_DEPT_OWNER)))
            .andExpect(jsonPath("$.[*].deptManagerUseridList").value(hasItem(DEFAULT_DEPT_MANAGER_USERID_LIST)))
            .andExpect(jsonPath("$.[*].sourceIdentifier").value(hasItem(DEFAULT_SOURCE_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].ext").value(hasItem(DEFAULT_EXT)))
            .andExpect(jsonPath("$.[*].isLeaf").value(hasItem(DEFAULT_IS_LEAF.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDdBookDept() throws Exception {
        // Initialize the database
        ddBookDeptRepository.saveAndFlush(ddBookDept);

        // Get the ddBookDept
        restDdBookDeptMockMvc.perform(get("/api/dd-book-depts/{id}", ddBookDept.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ddBookDept.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM.intValue()))
            .andExpect(jsonPath("$.parentid").value(DEFAULT_PARENTID.intValue()))
            .andExpect(jsonPath("$.createDeptGroup").value(DEFAULT_CREATE_DEPT_GROUP.booleanValue()))
            .andExpect(jsonPath("$.autoAddUser").value(DEFAULT_AUTO_ADD_USER.booleanValue()))
            .andExpect(jsonPath("$.deptHiding").value(DEFAULT_DEPT_HIDING.booleanValue()))
            .andExpect(jsonPath("$.deptPermits").value(DEFAULT_DEPT_PERMITS))
            .andExpect(jsonPath("$.userPermits").value(DEFAULT_USER_PERMITS))
            .andExpect(jsonPath("$.outerDept").value(DEFAULT_OUTER_DEPT.booleanValue()))
            .andExpect(jsonPath("$.outerPermitDepts").value(DEFAULT_OUTER_PERMIT_DEPTS))
            .andExpect(jsonPath("$.outerPermitUsers").value(DEFAULT_OUTER_PERMIT_USERS))
            .andExpect(jsonPath("$.orgDeptOwner").value(DEFAULT_ORG_DEPT_OWNER))
            .andExpect(jsonPath("$.deptManagerUseridList").value(DEFAULT_DEPT_MANAGER_USERID_LIST))
            .andExpect(jsonPath("$.sourceIdentifier").value(DEFAULT_SOURCE_IDENTIFIER))
            .andExpect(jsonPath("$.ext").value(DEFAULT_EXT))
            .andExpect(jsonPath("$.isLeaf").value(DEFAULT_IS_LEAF.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDdBookDept() throws Exception {
        // Get the ddBookDept
        restDdBookDeptMockMvc.perform(get("/api/dd-book-depts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDdBookDept() throws Exception {
        // Initialize the database
        ddBookDeptService.save(ddBookDept);

        int databaseSizeBeforeUpdate = ddBookDeptRepository.findAll().size();

        // Update the ddBookDept
        DdBookDept updatedDdBookDept = ddBookDeptRepository.findById(ddBookDept.getId()).get();
        // Disconnect from session so that the updates on updatedDdBookDept are not directly saved in db
        em.detach(updatedDdBookDept);
        updatedDdBookDept
            .name(UPDATED_NAME)
            .orderNum(UPDATED_ORDER_NUM)
            .parentid(UPDATED_PARENTID)
            .createDeptGroup(UPDATED_CREATE_DEPT_GROUP)
            .autoAddUser(UPDATED_AUTO_ADD_USER)
            .deptHiding(UPDATED_DEPT_HIDING)
            .deptPermits(UPDATED_DEPT_PERMITS)
            .userPermits(UPDATED_USER_PERMITS)
            .outerDept(UPDATED_OUTER_DEPT)
            .outerPermitDepts(UPDATED_OUTER_PERMIT_DEPTS)
            .outerPermitUsers(UPDATED_OUTER_PERMIT_USERS)
            .orgDeptOwner(UPDATED_ORG_DEPT_OWNER)
            .deptManagerUseridList(UPDATED_DEPT_MANAGER_USERID_LIST)
            .sourceIdentifier(UPDATED_SOURCE_IDENTIFIER)
            .ext(UPDATED_EXT)
            .isLeaf(UPDATED_IS_LEAF);

        restDdBookDeptMockMvc.perform(put("/api/dd-book-depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDdBookDept)))
            .andExpect(status().isOk());

        // Validate the DdBookDept in the database
        List<DdBookDept> ddBookDeptList = ddBookDeptRepository.findAll();
        assertThat(ddBookDeptList).hasSize(databaseSizeBeforeUpdate);
        DdBookDept testDdBookDept = ddBookDeptList.get(ddBookDeptList.size() - 1);
        assertThat(testDdBookDept.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDdBookDept.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testDdBookDept.getParentid()).isEqualTo(UPDATED_PARENTID);
        assertThat(testDdBookDept.isCreateDeptGroup()).isEqualTo(UPDATED_CREATE_DEPT_GROUP);
        assertThat(testDdBookDept.isAutoAddUser()).isEqualTo(UPDATED_AUTO_ADD_USER);
        assertThat(testDdBookDept.isDeptHiding()).isEqualTo(UPDATED_DEPT_HIDING);
        assertThat(testDdBookDept.getDeptPermits()).isEqualTo(UPDATED_DEPT_PERMITS);
        assertThat(testDdBookDept.getUserPermits()).isEqualTo(UPDATED_USER_PERMITS);
        assertThat(testDdBookDept.isOuterDept()).isEqualTo(UPDATED_OUTER_DEPT);
        assertThat(testDdBookDept.getOuterPermitDepts()).isEqualTo(UPDATED_OUTER_PERMIT_DEPTS);
        assertThat(testDdBookDept.getOuterPermitUsers()).isEqualTo(UPDATED_OUTER_PERMIT_USERS);
        assertThat(testDdBookDept.getOrgDeptOwner()).isEqualTo(UPDATED_ORG_DEPT_OWNER);
        assertThat(testDdBookDept.getDeptManagerUseridList()).isEqualTo(UPDATED_DEPT_MANAGER_USERID_LIST);
        assertThat(testDdBookDept.getSourceIdentifier()).isEqualTo(UPDATED_SOURCE_IDENTIFIER);
        assertThat(testDdBookDept.getExt()).isEqualTo(UPDATED_EXT);
        assertThat(testDdBookDept.isIsLeaf()).isEqualTo(UPDATED_IS_LEAF);
    }

    @Test
    @Transactional
    public void updateNonExistingDdBookDept() throws Exception {
        int databaseSizeBeforeUpdate = ddBookDeptRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDdBookDeptMockMvc.perform(put("/api/dd-book-depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddBookDept)))
            .andExpect(status().isBadRequest());

        // Validate the DdBookDept in the database
        List<DdBookDept> ddBookDeptList = ddBookDeptRepository.findAll();
        assertThat(ddBookDeptList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDdBookDept() throws Exception {
        // Initialize the database
        ddBookDeptService.save(ddBookDept);

        int databaseSizeBeforeDelete = ddBookDeptRepository.findAll().size();

        // Delete the ddBookDept
        restDdBookDeptMockMvc.perform(delete("/api/dd-book-depts/{id}", ddBookDept.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DdBookDept> ddBookDeptList = ddBookDeptRepository.findAll();
        assertThat(ddBookDeptList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

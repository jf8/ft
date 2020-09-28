package com.kyanite.ft.web.rest;

import com.kyanite.ft.RedisTestContainerExtension;
import com.kyanite.ft.FtApp;
import com.kyanite.ft.domain.DdBookPerson;
import com.kyanite.ft.repository.DdBookPersonRepository;
import com.kyanite.ft.service.DdBookPersonService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DdBookPersonResource} REST controller.
 */
@SpringBootTest(classes = FtApp.class)
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class DdBookPersonResourceIT {

    private static final String DEFAULT_UNIONID = "AAAAAAAAAA";
    private static final String UPDATED_UNIONID = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_IS_LEADER_IN_DEPTS = "AAAAAAAAAA";
    private static final String UPDATED_IS_LEADER_IN_DEPTS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_BOSS = false;
    private static final Boolean UPDATED_IS_BOSS = true;

    private static final BigDecimal DEFAULT_HIRED_DATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_HIRED_DATE = new BigDecimal(2);

    private static final Boolean DEFAULT_IS_SENIOR = false;
    private static final Boolean UPDATED_IS_SENIOR = true;

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_IN_DEPTS = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_IN_DEPTS = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_ERRMSG = "AAAAAAAAAA";
    private static final String UPDATED_ERRMSG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ADMIN = false;
    private static final Boolean UPDATED_IS_ADMIN = true;

    private static final Boolean DEFAULT_IS_HIDE = false;
    private static final Boolean UPDATED_IS_HIDE = true;

    private static final String DEFAULT_JOBNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_JOBNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXTATTR = "AAAAAAAAAA";
    private static final String UPDATED_EXTATTR = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_ROLES = "AAAAAAAAAA";
    private static final String UPDATED_ROLES = "BBBBBBBBBB";

    @Autowired
    private DdBookPersonRepository ddBookPersonRepository;

    @Autowired
    private DdBookPersonService ddBookPersonService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDdBookPersonMockMvc;

    private DdBookPerson ddBookPerson;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DdBookPerson createEntity(EntityManager em) {
        DdBookPerson ddBookPerson = new DdBookPerson()
            .unionid(DEFAULT_UNIONID)
            .remark(DEFAULT_REMARK)
            .userid(DEFAULT_USERID)
            .isLeaderInDepts(DEFAULT_IS_LEADER_IN_DEPTS)
            .isBoss(DEFAULT_IS_BOSS)
            .hiredDate(DEFAULT_HIRED_DATE)
            .isSenior(DEFAULT_IS_SENIOR)
            .tel(DEFAULT_TEL)
            .department(DEFAULT_DEPARTMENT)
            .email(DEFAULT_EMAIL)
            .workPlace(DEFAULT_WORK_PLACE)
            .orderInDepts(DEFAULT_ORDER_IN_DEPTS)
            .mobile(DEFAULT_MOBILE)
            .errmsg(DEFAULT_ERRMSG)
            .active(DEFAULT_ACTIVE)
            .avatar(DEFAULT_AVATAR)
            .isAdmin(DEFAULT_IS_ADMIN)
            .isHide(DEFAULT_IS_HIDE)
            .jobnumber(DEFAULT_JOBNUMBER)
            .name(DEFAULT_NAME)
            .extattr(DEFAULT_EXTATTR)
            .stateCode(DEFAULT_STATE_CODE)
            .position(DEFAULT_POSITION)
            .roles(DEFAULT_ROLES);
        return ddBookPerson;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DdBookPerson createUpdatedEntity(EntityManager em) {
        DdBookPerson ddBookPerson = new DdBookPerson()
            .unionid(UPDATED_UNIONID)
            .remark(UPDATED_REMARK)
            .userid(UPDATED_USERID)
            .isLeaderInDepts(UPDATED_IS_LEADER_IN_DEPTS)
            .isBoss(UPDATED_IS_BOSS)
            .hiredDate(UPDATED_HIRED_DATE)
            .isSenior(UPDATED_IS_SENIOR)
            .tel(UPDATED_TEL)
            .department(UPDATED_DEPARTMENT)
            .email(UPDATED_EMAIL)
            .workPlace(UPDATED_WORK_PLACE)
            .orderInDepts(UPDATED_ORDER_IN_DEPTS)
            .mobile(UPDATED_MOBILE)
            .errmsg(UPDATED_ERRMSG)
            .active(UPDATED_ACTIVE)
            .avatar(UPDATED_AVATAR)
            .isAdmin(UPDATED_IS_ADMIN)
            .isHide(UPDATED_IS_HIDE)
            .jobnumber(UPDATED_JOBNUMBER)
            .name(UPDATED_NAME)
            .extattr(UPDATED_EXTATTR)
            .stateCode(UPDATED_STATE_CODE)
            .position(UPDATED_POSITION)
            .roles(UPDATED_ROLES);
        return ddBookPerson;
    }

    @BeforeEach
    public void initTest() {
        ddBookPerson = createEntity(em);
    }

    @Test
    @Transactional
    public void createDdBookPerson() throws Exception {
        int databaseSizeBeforeCreate = ddBookPersonRepository.findAll().size();
        // Create the DdBookPerson
        restDdBookPersonMockMvc.perform(post("/api/dd-book-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddBookPerson)))
            .andExpect(status().isCreated());

        // Validate the DdBookPerson in the database
        List<DdBookPerson> ddBookPersonList = ddBookPersonRepository.findAll();
        assertThat(ddBookPersonList).hasSize(databaseSizeBeforeCreate + 1);
        DdBookPerson testDdBookPerson = ddBookPersonList.get(ddBookPersonList.size() - 1);
        assertThat(testDdBookPerson.getUnionid()).isEqualTo(DEFAULT_UNIONID);
        assertThat(testDdBookPerson.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testDdBookPerson.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testDdBookPerson.getIsLeaderInDepts()).isEqualTo(DEFAULT_IS_LEADER_IN_DEPTS);
        assertThat(testDdBookPerson.isIsBoss()).isEqualTo(DEFAULT_IS_BOSS);
        assertThat(testDdBookPerson.getHiredDate()).isEqualTo(DEFAULT_HIRED_DATE);
        assertThat(testDdBookPerson.isIsSenior()).isEqualTo(DEFAULT_IS_SENIOR);
        assertThat(testDdBookPerson.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testDdBookPerson.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testDdBookPerson.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDdBookPerson.getWorkPlace()).isEqualTo(DEFAULT_WORK_PLACE);
        assertThat(testDdBookPerson.getOrderInDepts()).isEqualTo(DEFAULT_ORDER_IN_DEPTS);
        assertThat(testDdBookPerson.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testDdBookPerson.getErrmsg()).isEqualTo(DEFAULT_ERRMSG);
        assertThat(testDdBookPerson.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testDdBookPerson.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testDdBookPerson.isIsAdmin()).isEqualTo(DEFAULT_IS_ADMIN);
        assertThat(testDdBookPerson.isIsHide()).isEqualTo(DEFAULT_IS_HIDE);
        assertThat(testDdBookPerson.getJobnumber()).isEqualTo(DEFAULT_JOBNUMBER);
        assertThat(testDdBookPerson.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDdBookPerson.getExtattr()).isEqualTo(DEFAULT_EXTATTR);
        assertThat(testDdBookPerson.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testDdBookPerson.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testDdBookPerson.getRoles()).isEqualTo(DEFAULT_ROLES);
    }

    @Test
    @Transactional
    public void createDdBookPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ddBookPersonRepository.findAll().size();

        // Create the DdBookPerson with an existing ID
        ddBookPerson.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDdBookPersonMockMvc.perform(post("/api/dd-book-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddBookPerson)))
            .andExpect(status().isBadRequest());

        // Validate the DdBookPerson in the database
        List<DdBookPerson> ddBookPersonList = ddBookPersonRepository.findAll();
        assertThat(ddBookPersonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDdBookPeople() throws Exception {
        // Initialize the database
        ddBookPersonRepository.saveAndFlush(ddBookPerson);

        // Get all the ddBookPersonList
        restDdBookPersonMockMvc.perform(get("/api/dd-book-people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ddBookPerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].unionid").value(hasItem(DEFAULT_UNIONID)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].isLeaderInDepts").value(hasItem(DEFAULT_IS_LEADER_IN_DEPTS)))
            .andExpect(jsonPath("$.[*].isBoss").value(hasItem(DEFAULT_IS_BOSS.booleanValue())))
            .andExpect(jsonPath("$.[*].hiredDate").value(hasItem(DEFAULT_HIRED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].isSenior").value(hasItem(DEFAULT_IS_SENIOR.booleanValue())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].workPlace").value(hasItem(DEFAULT_WORK_PLACE)))
            .andExpect(jsonPath("$.[*].orderInDepts").value(hasItem(DEFAULT_ORDER_IN_DEPTS)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].errmsg").value(hasItem(DEFAULT_ERRMSG)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.[*].isAdmin").value(hasItem(DEFAULT_IS_ADMIN.booleanValue())))
            .andExpect(jsonPath("$.[*].isHide").value(hasItem(DEFAULT_IS_HIDE.booleanValue())))
            .andExpect(jsonPath("$.[*].jobnumber").value(hasItem(DEFAULT_JOBNUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].extattr").value(hasItem(DEFAULT_EXTATTR)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].roles").value(hasItem(DEFAULT_ROLES)));
    }
    
    @Test
    @Transactional
    public void getDdBookPerson() throws Exception {
        // Initialize the database
        ddBookPersonRepository.saveAndFlush(ddBookPerson);

        // Get the ddBookPerson
        restDdBookPersonMockMvc.perform(get("/api/dd-book-people/{id}", ddBookPerson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ddBookPerson.getId().intValue()))
            .andExpect(jsonPath("$.unionid").value(DEFAULT_UNIONID))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.isLeaderInDepts").value(DEFAULT_IS_LEADER_IN_DEPTS))
            .andExpect(jsonPath("$.isBoss").value(DEFAULT_IS_BOSS.booleanValue()))
            .andExpect(jsonPath("$.hiredDate").value(DEFAULT_HIRED_DATE.intValue()))
            .andExpect(jsonPath("$.isSenior").value(DEFAULT_IS_SENIOR.booleanValue()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.workPlace").value(DEFAULT_WORK_PLACE))
            .andExpect(jsonPath("$.orderInDepts").value(DEFAULT_ORDER_IN_DEPTS))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.errmsg").value(DEFAULT_ERRMSG))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR))
            .andExpect(jsonPath("$.isAdmin").value(DEFAULT_IS_ADMIN.booleanValue()))
            .andExpect(jsonPath("$.isHide").value(DEFAULT_IS_HIDE.booleanValue()))
            .andExpect(jsonPath("$.jobnumber").value(DEFAULT_JOBNUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.extattr").value(DEFAULT_EXTATTR))
            .andExpect(jsonPath("$.stateCode").value(DEFAULT_STATE_CODE))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.roles").value(DEFAULT_ROLES));
    }
    @Test
    @Transactional
    public void getNonExistingDdBookPerson() throws Exception {
        // Get the ddBookPerson
        restDdBookPersonMockMvc.perform(get("/api/dd-book-people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDdBookPerson() throws Exception {
        // Initialize the database
        ddBookPersonService.save(ddBookPerson);

        int databaseSizeBeforeUpdate = ddBookPersonRepository.findAll().size();

        // Update the ddBookPerson
        DdBookPerson updatedDdBookPerson = ddBookPersonRepository.findById(ddBookPerson.getId()).get();
        // Disconnect from session so that the updates on updatedDdBookPerson are not directly saved in db
        em.detach(updatedDdBookPerson);
        updatedDdBookPerson
            .unionid(UPDATED_UNIONID)
            .remark(UPDATED_REMARK)
            .userid(UPDATED_USERID)
            .isLeaderInDepts(UPDATED_IS_LEADER_IN_DEPTS)
            .isBoss(UPDATED_IS_BOSS)
            .hiredDate(UPDATED_HIRED_DATE)
            .isSenior(UPDATED_IS_SENIOR)
            .tel(UPDATED_TEL)
            .department(UPDATED_DEPARTMENT)
            .email(UPDATED_EMAIL)
            .workPlace(UPDATED_WORK_PLACE)
            .orderInDepts(UPDATED_ORDER_IN_DEPTS)
            .mobile(UPDATED_MOBILE)
            .errmsg(UPDATED_ERRMSG)
            .active(UPDATED_ACTIVE)
            .avatar(UPDATED_AVATAR)
            .isAdmin(UPDATED_IS_ADMIN)
            .isHide(UPDATED_IS_HIDE)
            .jobnumber(UPDATED_JOBNUMBER)
            .name(UPDATED_NAME)
            .extattr(UPDATED_EXTATTR)
            .stateCode(UPDATED_STATE_CODE)
            .position(UPDATED_POSITION)
            .roles(UPDATED_ROLES);

        restDdBookPersonMockMvc.perform(put("/api/dd-book-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDdBookPerson)))
            .andExpect(status().isOk());

        // Validate the DdBookPerson in the database
        List<DdBookPerson> ddBookPersonList = ddBookPersonRepository.findAll();
        assertThat(ddBookPersonList).hasSize(databaseSizeBeforeUpdate);
        DdBookPerson testDdBookPerson = ddBookPersonList.get(ddBookPersonList.size() - 1);
        assertThat(testDdBookPerson.getUnionid()).isEqualTo(UPDATED_UNIONID);
        assertThat(testDdBookPerson.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testDdBookPerson.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testDdBookPerson.getIsLeaderInDepts()).isEqualTo(UPDATED_IS_LEADER_IN_DEPTS);
        assertThat(testDdBookPerson.isIsBoss()).isEqualTo(UPDATED_IS_BOSS);
        assertThat(testDdBookPerson.getHiredDate()).isEqualTo(UPDATED_HIRED_DATE);
        assertThat(testDdBookPerson.isIsSenior()).isEqualTo(UPDATED_IS_SENIOR);
        assertThat(testDdBookPerson.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testDdBookPerson.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testDdBookPerson.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDdBookPerson.getWorkPlace()).isEqualTo(UPDATED_WORK_PLACE);
        assertThat(testDdBookPerson.getOrderInDepts()).isEqualTo(UPDATED_ORDER_IN_DEPTS);
        assertThat(testDdBookPerson.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testDdBookPerson.getErrmsg()).isEqualTo(UPDATED_ERRMSG);
        assertThat(testDdBookPerson.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testDdBookPerson.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testDdBookPerson.isIsAdmin()).isEqualTo(UPDATED_IS_ADMIN);
        assertThat(testDdBookPerson.isIsHide()).isEqualTo(UPDATED_IS_HIDE);
        assertThat(testDdBookPerson.getJobnumber()).isEqualTo(UPDATED_JOBNUMBER);
        assertThat(testDdBookPerson.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDdBookPerson.getExtattr()).isEqualTo(UPDATED_EXTATTR);
        assertThat(testDdBookPerson.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testDdBookPerson.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testDdBookPerson.getRoles()).isEqualTo(UPDATED_ROLES);
    }

    @Test
    @Transactional
    public void updateNonExistingDdBookPerson() throws Exception {
        int databaseSizeBeforeUpdate = ddBookPersonRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDdBookPersonMockMvc.perform(put("/api/dd-book-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddBookPerson)))
            .andExpect(status().isBadRequest());

        // Validate the DdBookPerson in the database
        List<DdBookPerson> ddBookPersonList = ddBookPersonRepository.findAll();
        assertThat(ddBookPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDdBookPerson() throws Exception {
        // Initialize the database
        ddBookPersonService.save(ddBookPerson);

        int databaseSizeBeforeDelete = ddBookPersonRepository.findAll().size();

        // Delete the ddBookPerson
        restDdBookPersonMockMvc.perform(delete("/api/dd-book-people/{id}", ddBookPerson.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DdBookPerson> ddBookPersonList = ddBookPersonRepository.findAll();
        assertThat(ddBookPersonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

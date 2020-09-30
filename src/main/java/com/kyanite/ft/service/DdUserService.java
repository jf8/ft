package com.kyanite.ft.service;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.kyanite.ft.dingtalk.user.DingtalkUserService;
import com.kyanite.ft.domain.DdUser;
import com.kyanite.ft.repository.AuthorityRepository;
import com.kyanite.ft.repository.DdUserRepository;
import com.kyanite.ft.repository.UserRepository;
import com.kyanite.ft.security.AuthoritiesConstants;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static com.kyanite.ft.config.Constants.DEFAULT_DD_PASSWORD;

/**
 * Service Implementation for managing {@link DdUser}.
 */
@Service
@Transactional
public class DdUserService {

    private final Logger log = LoggerFactory.getLogger(DdUserService.class);

    private final DdUserRepository ddUserRepository;

    public DdUserService(DdUserRepository ddUserRepository) {
        this.ddUserRepository = ddUserRepository;
    }

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    DingtalkUserService dingtalkUserService;



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DdUser createDdUserFromDingtalk(String userid) throws ApiException {
        long startTime;
        DdUser user;
        startTime = Instant.now().toEpochMilli();
        OapiUserGetResponse rsp = dingtalkUserService.getUser(userid);
        log.info("get user by dingtalk:{}, userInfo: {} ",(Instant.now().toEpochMilli() - startTime) +"ms", JSON.toJSONString(rsp));
        user = new DdUser();
        Optional<DdUser> oneByEmailIgnoreCase = ddUserRepository.findFirstByEmailIgnoreCase(rsp.getEmail());
        if(!oneByEmailIgnoreCase.isPresent()&& StringUtils.isNotBlank(rsp.getEmail())){
            user.setEmail(rsp.getEmail());
        }
        user.setUserid(rsp.getUserid());
        user.setLogin(rsp.getUserid());
        user.setActive(rsp.getActive());
        user.setDepartment(rsp.getDepartment()==null?null: JSON.toJSONString(rsp.getDepartment()));
        user.setAvatar(rsp.getAvatar());
        user.setImageUrl(rsp.getAvatar());
        user.setExtattr(rsp.getExtattr());
        user.setHiredDate(BigDecimal.valueOf(rsp.getHiredDate()==null?0L:rsp.getHiredDate().getTime()));
        user.setJobnumber(rsp.getJobnumber());
        user.setIsLeaderInDepts(rsp.getIsLeaderInDepts());
        user.setMobile(rsp.getMobile());
        user.setName(rsp.getName());
        user.setOrderInDepts(rsp.getOrderInDepts());
        user.setPosition(rsp.getPosition());
        user.setRemark(rsp.getRemark());
        user.setStateCode(rsp.getStateCode());
        user.setTel(rsp.getTel());
        user.setUnionid(rsp.getUnionid());
        user.setWorkPlace(rsp.getWorkPlace());
        user.setCreatedBy("dingtalk");
        user.setActivated(Boolean.TRUE);
        user.setFirstName(rsp.getName());
        user.setAuthorities(authorityRepository.findFirstByName(AuthoritiesConstants.DD));
        String encryptedPassword = passwordEncoder.encode(DEFAULT_DD_PASSWORD);
        user.setPassword(encryptedPassword);
        DdUser save = ddUserRepository.saveAndFlush(user);
        if(save.getLogin()!=null&&cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)!=null){
            Cache.ValueWrapper valueWrapper = cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).get(save.getLogin());
            if(valueWrapper!=null){
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(save.getLogin());
            }
        }
        if(save.getEmail()!=null&&cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)!=null){
            Cache.ValueWrapper valueWrapper = cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).get(save.getEmail());
            if(valueWrapper!=null){
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(save.getEmail());
            }
        }
//        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(save.getLogin());
//        if(save.getEmail()!=null)
//            Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(save.getEmail());
        return save;
    }



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DdUser updateDdUserFromDingtalk(String userid) throws ApiException {
        long startTime;
        DdUser user;
        startTime = Instant.now().toEpochMilli();
        OapiUserGetResponse rsp = dingtalkUserService.getUser(userid);
        log.info("get user by dingtalk:{}, userInfo: {} ",(Instant.now().toEpochMilli() - startTime) +"ms", JSON.toJSONString(rsp));
        Optional<DdUser> oneByLogin = ddUserRepository.findOneByLogin(userid);
        user = oneByLogin.get();
        Optional<DdUser> oneByEmailIgnoreCase = ddUserRepository.findFirstByEmailIgnoreCase(rsp.getEmail());
        if(!oneByEmailIgnoreCase.isPresent()&& StringUtils.isNotBlank(rsp.getEmail())){
            user.setEmail(rsp.getEmail());
        }
        user.setUserid(rsp.getUserid());
        user.setLogin(rsp.getUserid());
        user.setActive(rsp.getActive());
        user.setDepartment(rsp.getDepartment()==null?null: JSON.toJSONString(rsp.getDepartment()));
        user.setAvatar(rsp.getAvatar());
        user.setImageUrl(rsp.getAvatar());
        user.setExtattr(rsp.getExtattr());
        user.setHiredDate(BigDecimal.valueOf(rsp.getHiredDate()==null?0L:rsp.getHiredDate().getTime()));
        user.setJobnumber(rsp.getJobnumber());
        user.setIsLeaderInDepts(rsp.getIsLeaderInDepts());
        user.setMobile(rsp.getMobile());
        user.setName(rsp.getName());
        user.setOrderInDepts(rsp.getOrderInDepts());
        user.setPosition(rsp.getPosition());
        user.setRemark(rsp.getRemark());
        user.setStateCode(rsp.getStateCode());
        user.setTel(rsp.getTel());
        user.setUnionid(rsp.getUnionid());
        user.setWorkPlace(rsp.getWorkPlace());
        user.setCreatedBy("dingtalk");
        user.setActivated(Boolean.TRUE);
        user.setAuthorities(authorityRepository.findFirstByName(AuthoritiesConstants.DD));
        String encryptedPassword = passwordEncoder.encode(DEFAULT_DD_PASSWORD);
        user.setPassword(encryptedPassword);
        DdUser save = ddUserRepository.save(user);
        if(user.getLogin()!=null&&cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)!=null){
            Cache.ValueWrapper valueWrapper = cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).get(user.getLogin());
            if(valueWrapper!=null){
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
            }
        }
        if(user.getEmail()!=null&&cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)!=null){
            Cache.ValueWrapper valueWrapper = cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).get(user.getEmail());
            if(valueWrapper!=null){
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
            }
        }
        return save;
    }

    /**
     * Save a ddUser.
     *
     * @param ddUser the entity to save.
     * @return the persisted entity.
     */
    public DdUser save(DdUser ddUser) {
        log.debug("Request to save DdUser : {}", ddUser);
        return ddUserRepository.save(ddUser);
    }

    /**
     * Get all the ddUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DdUser> findAll(Pageable pageable) {
        log.debug("Request to get all DdUsers");
        return ddUserRepository.findAll(pageable);
    }


    /**
     * Get all the ddUsers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DdUser> findAllWithEagerRelationships(Pageable pageable) {
        return ddUserRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one ddUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DdUser> findOne(Long id) {
        log.debug("Request to get DdUser : {}", id);
        return ddUserRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the ddUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DdUser : {}", id);
        ddUserRepository.deleteById(id);
    }
}

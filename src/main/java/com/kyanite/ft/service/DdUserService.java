package com.kyanite.ft.service;

import com.kyanite.ft.domain.DdUser;
import com.kyanite.ft.repository.DdUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

package com.kyanite.ft.service;

import com.kyanite.ft.domain.DdBookDept;
import com.kyanite.ft.repository.DdBookDeptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DdBookDept}.
 */
@Service
@Transactional
public class DdBookDeptService {

    private final Logger log = LoggerFactory.getLogger(DdBookDeptService.class);

    private final DdBookDeptRepository ddBookDeptRepository;

    public DdBookDeptService(DdBookDeptRepository ddBookDeptRepository) {
        this.ddBookDeptRepository = ddBookDeptRepository;
    }

    /**
     * Save a ddBookDept.
     *
     * @param ddBookDept the entity to save.
     * @return the persisted entity.
     */
    public DdBookDept save(DdBookDept ddBookDept) {
        log.debug("Request to save DdBookDept : {}", ddBookDept);
        return ddBookDeptRepository.save(ddBookDept);
    }

    /**
     * Get all the ddBookDepts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DdBookDept> findAll(Pageable pageable) {
        log.debug("Request to get all DdBookDepts");
        return ddBookDeptRepository.findAll(pageable);
    }


    /**
     * Get all the ddBookDepts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DdBookDept> findAllWithEagerRelationships(Pageable pageable) {
        return ddBookDeptRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one ddBookDept by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DdBookDept> findOne(Long id) {
        log.debug("Request to get DdBookDept : {}", id);
        return ddBookDeptRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the ddBookDept by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DdBookDept : {}", id);
        ddBookDeptRepository.deleteById(id);
    }
}

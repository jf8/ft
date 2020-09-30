package com.kyanite.ft.service;

import com.kyanite.ft.domain.DdBookPerson;
import com.kyanite.ft.repository.DdBookPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DdBookPerson}.
 */
@Service
@Transactional
public class DdBookPersonService {

    private final Logger log = LoggerFactory.getLogger(DdBookPersonService.class);

    private final DdBookPersonRepository ddBookPersonRepository;

    public DdBookPersonService(DdBookPersonRepository ddBookPersonRepository) {
        this.ddBookPersonRepository = ddBookPersonRepository;
    }

    /**
     * Save a ddBookPerson.
     *
     * @param ddBookPerson the entity to save.
     * @return the persisted entity.
     */
    public DdBookPerson save(DdBookPerson ddBookPerson) {
        log.debug("Request to save DdBookPerson : {}", ddBookPerson);
        return ddBookPersonRepository.save(ddBookPerson);
    }

    /**
     * Get all the ddBookPeople.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DdBookPerson> findAll(Pageable pageable) {
        log.debug("Request to get all DdBookPeople");
        return ddBookPersonRepository.findAll(pageable);
    }


    /**
     * Get one ddBookPerson by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DdBookPerson> findOne(String id) {
        log.debug("Request to get DdBookPerson : {}", id);
        return ddBookPersonRepository.findById(id);
    }

    /**
     * Delete the ddBookPerson by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete DdBookPerson : {}", id);
        ddBookPersonRepository.deleteById(id);
    }
}

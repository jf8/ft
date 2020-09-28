package com.kyanite.ft.service;

import com.kyanite.ft.domain.LiveSharing;
import com.kyanite.ft.repository.LiveSharingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LiveSharing}.
 */
@Service
@Transactional
public class LiveSharingService {

    private final Logger log = LoggerFactory.getLogger(LiveSharingService.class);

    private final LiveSharingRepository liveSharingRepository;

    public LiveSharingService(LiveSharingRepository liveSharingRepository) {
        this.liveSharingRepository = liveSharingRepository;
    }

    /**
     * Save a liveSharing.
     *
     * @param liveSharing the entity to save.
     * @return the persisted entity.
     */
    public LiveSharing save(LiveSharing liveSharing) {
        log.debug("Request to save LiveSharing : {}", liveSharing);
        return liveSharingRepository.save(liveSharing);
    }

    /**
     * Get all the liveSharings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LiveSharing> findAll(Pageable pageable) {
        log.debug("Request to get all LiveSharings");
        return liveSharingRepository.findAll(pageable);
    }


    /**
     * Get one liveSharing by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LiveSharing> findOne(Long id) {
        log.debug("Request to get LiveSharing : {}", id);
        return liveSharingRepository.findById(id);
    }

    /**
     * Delete the liveSharing by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LiveSharing : {}", id);
        liveSharingRepository.deleteById(id);
    }
}

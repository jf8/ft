package com.kyanite.ft.service;

import com.kyanite.ft.domain.VFtUserSignInfo;
import com.kyanite.ft.repository.VFtUserSignInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VFtUserSignInfo}.
 */
@Service
@Transactional
public class VFtUserSignInfoService {

    private final Logger log = LoggerFactory.getLogger(VFtUserSignInfoService.class);

    private final VFtUserSignInfoRepository vFtUserSignInfoRepository;

    public VFtUserSignInfoService(VFtUserSignInfoRepository vFtUserSignInfoRepository) {
        this.vFtUserSignInfoRepository = vFtUserSignInfoRepository;
    }

    /**
     * Save a vFtUserSignInfo.
     *
     * @param vFtUserSignInfo the entity to save.
     * @return the persisted entity.
     */
    public VFtUserSignInfo save(VFtUserSignInfo vFtUserSignInfo) {
        log.debug("Request to save VFtUserSignInfo : {}", vFtUserSignInfo);
        return vFtUserSignInfoRepository.save(vFtUserSignInfo);
    }

    /**
     * Get all the vFtUserSignInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VFtUserSignInfo> findAll(Pageable pageable) {
        log.debug("Request to get all VFtUserSignInfos");
        return vFtUserSignInfoRepository.findAll(pageable);
    }


    /**
     * Get one vFtUserSignInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VFtUserSignInfo> findOne(Long id) {
        log.debug("Request to get VFtUserSignInfo : {}", id);
        return vFtUserSignInfoRepository.findById(id);
    }

    /**
     * Delete the vFtUserSignInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete VFtUserSignInfo : {}", id);
        vFtUserSignInfoRepository.deleteById(id);
    }
}

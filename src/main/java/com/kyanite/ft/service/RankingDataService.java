package com.kyanite.ft.service;

import com.kyanite.ft.domain.RankingData;
import com.kyanite.ft.repository.RankingDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RankingData}.
 */
@Service
@Transactional
public class RankingDataService {

    private final Logger log = LoggerFactory.getLogger(RankingDataService.class);

    private final RankingDataRepository rankingDataRepository;

    public RankingDataService(RankingDataRepository rankingDataRepository) {
        this.rankingDataRepository = rankingDataRepository;
    }

    /**
     * Save a rankingData.
     *
     * @param rankingData the entity to save.
     * @return the persisted entity.
     */
    public RankingData save(RankingData rankingData) {
        log.debug("Request to save RankingData : {}", rankingData);
        return rankingDataRepository.save(rankingData);
    }

    /**
     * Get all the rankingData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RankingData> findAll(Pageable pageable) {
        log.debug("Request to get all RankingData");
        return rankingDataRepository.findAll(pageable);
    }


    /**
     * Get one rankingData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RankingData> findOne(Long id) {
        log.debug("Request to get RankingData : {}", id);
        return rankingDataRepository.findById(id);
    }

    /**
     * Delete the rankingData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RankingData : {}", id);
        rankingDataRepository.deleteById(id);
    }
}

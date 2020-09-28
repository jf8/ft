package com.kyanite.ft.repository;

import com.kyanite.ft.domain.RankingData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RankingData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RankingDataRepository extends JpaRepository<RankingData, Long> {
}

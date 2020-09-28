package com.kyanite.ft.repository;

import com.kyanite.ft.domain.LiveSharing;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LiveSharing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LiveSharingRepository extends JpaRepository<LiveSharing, Long> {
}

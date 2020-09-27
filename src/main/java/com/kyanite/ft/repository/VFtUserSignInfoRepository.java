package com.kyanite.ft.repository;

import com.kyanite.ft.domain.VFtUserSignInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VFtUserSignInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VFtUserSignInfoRepository extends JpaRepository<VFtUserSignInfo, Long> {
}

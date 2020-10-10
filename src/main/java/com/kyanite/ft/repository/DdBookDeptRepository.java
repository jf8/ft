package com.kyanite.ft.repository;

import com.kyanite.ft.domain.DdBookDept;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DdBookDept entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DdBookDeptRepository extends JpaRepository<DdBookDept, Long> {
}

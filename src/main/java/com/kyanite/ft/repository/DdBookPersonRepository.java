package com.kyanite.ft.repository;

import com.kyanite.ft.domain.DdBookPerson;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DdBookPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DdBookPersonRepository extends JpaRepository<DdBookPerson, Long> {
}

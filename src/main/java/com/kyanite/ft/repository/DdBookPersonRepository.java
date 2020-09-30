package com.kyanite.ft.repository;

import com.kyanite.ft.domain.DdBookPerson;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring Data  repository for the DdBookPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DdBookPersonRepository extends JpaRepository<DdBookPerson, String> {
    Long countAllByParentDeptsIdListLike(String deptID);
    Set<DdBookPerson> findAllByParentDeptsIdListLike(String deptID);
}

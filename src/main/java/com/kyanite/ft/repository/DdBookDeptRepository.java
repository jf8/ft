package com.kyanite.ft.repository;

import com.kyanite.ft.domain.DdBookDept;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DdBookDept entity.
 */
@Repository
public interface DdBookDeptRepository extends JpaRepository<DdBookDept, Long> {

    @Query(value = "select distinct ddBookDept from DdBookDept ddBookDept left join fetch ddBookDept.ddBookPeople",
        countQuery = "select count(distinct ddBookDept) from DdBookDept ddBookDept")
    Page<DdBookDept> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct ddBookDept from DdBookDept ddBookDept left join fetch ddBookDept.ddBookPeople")
    List<DdBookDept> findAllWithEagerRelationships();

    @Query("select ddBookDept from DdBookDept ddBookDept left join fetch ddBookDept.ddBookPeople where ddBookDept.id =:id")
    Optional<DdBookDept> findOneWithEagerRelationships(@Param("id") Long id);
}

package com.kyanite.ft.repository;

import com.kyanite.ft.domain.DdBookPerson;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DdBookPerson entity.
 */
@Repository
public interface DdBookPersonRepository extends JpaRepository<DdBookPerson, Long> {

    @Query(value = "select distinct ddBookPerson from DdBookPerson ddBookPerson left join fetch ddBookPerson.ddBookDepts",
        countQuery = "select count(distinct ddBookPerson) from DdBookPerson ddBookPerson")
    Page<DdBookPerson> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct ddBookPerson from DdBookPerson ddBookPerson left join fetch ddBookPerson.ddBookDepts")
    List<DdBookPerson> findAllWithEagerRelationships();

    @Query("select ddBookPerson from DdBookPerson ddBookPerson left join fetch ddBookPerson.ddBookDepts where ddBookPerson.id =:id")
    Optional<DdBookPerson> findOneWithEagerRelationships(@Param("id") Long id);
}

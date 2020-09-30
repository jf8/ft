package com.kyanite.ft.repository;

import com.kyanite.ft.domain.DdUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DdUser entity.
 */
@Repository
public interface DdUserRepository extends JpaRepository<DdUser, Long> {

    @Query(value = "select distinct ddUser from DdUser ddUser left join fetch ddUser.ddBookDepts",
        countQuery = "select count(distinct ddUser) from DdUser ddUser")
    Page<DdUser> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct ddUser from DdUser ddUser left join fetch ddUser.ddBookDepts")
    List<DdUser> findAllWithEagerRelationships();

    @Query("select ddUser from DdUser ddUser left join fetch ddUser.ddBookDepts where ddUser.id =:id")
    Optional<DdUser> findOneWithEagerRelationships(@Param("id") Long id);


    Optional<DdUser> findFirstByEmailIgnoreCase(String email);

    Optional<DdUser> findOneByLogin(String login);


}

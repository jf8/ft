package com.kyanite.ft.repository;

import com.kyanite.ft.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    public Set<Authority> findFirstByName(String name);
}

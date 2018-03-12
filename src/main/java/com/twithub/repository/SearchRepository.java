package com.twithub.repository;

import com.twithub.domain.Search;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Search entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SearchRepository extends JpaRepository<Search,Long> {
    
}

package com.chasermanager.repositories;

import com.chasermanager.domain.models.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
    Source findByName(String name);

}

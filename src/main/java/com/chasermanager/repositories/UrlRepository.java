package com.chasermanager.repositories;

import com.chasermanager.domain.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByLink(String link);

    @Query("select u from Url u " +
            "inner join Source s on s.id = u.source.id " +
            "where s.name=:sourceName and u.link=:url")
    Url findByUrlAndSourceName(String url, String sourceName);
}

package com.chasermanager.repositories;

import com.chasermanager.domain.models.Switcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwitcherRepository extends JpaRepository<Switcher, Long> {
    @Query("select s from Switcher s " +
            "where s.user.email=:email and s.url.id=:id")
    Switcher findByEmailAndUrlId(String email, Long id);

    @Query("select s from Switcher s " +
            "where s.status='START' and s.periodicity=:periodicity")
    List<Switcher> findAllByPeriodicity(long periodicity);
}

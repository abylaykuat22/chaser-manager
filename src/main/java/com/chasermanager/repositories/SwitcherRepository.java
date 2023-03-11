package com.chasermanager.repositories;

import com.chasermanager.domain.models.Switcher;
import com.chasermanager.domain.models.Url;
import com.chasermanager.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwitcherRepository extends JpaRepository<Switcher, Long> {

    @Query("select s from Switcher s " +
            "where s.status='START' and s.periodicity=:periodicity")
    List<Switcher> findAllByPeriodicity(long periodicity);

    boolean existsByUserAndUrl(User user, Url url);

    Page<Switcher> findAllByUser(User user, Pageable pageable);
}

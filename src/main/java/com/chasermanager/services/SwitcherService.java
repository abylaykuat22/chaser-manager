package com.chasermanager.services;

import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;
import com.chasermanager.domain.models.Switcher;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface SwitcherService {

    void create(String source, String link, Periodicity periodicity);

    void setStatus(SwitcherStatus status, Long id) throws IOException, MessagingException;

    void update(Long id, Periodicity periodicity);

    List<Switcher> findAllByPeriodicity(long periodicity);

    void run(Switcher switcher) throws IOException, MessagingException;
}

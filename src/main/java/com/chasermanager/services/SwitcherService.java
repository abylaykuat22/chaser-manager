package com.chasermanager.services;

import com.chasermanager.domain.dto.SwitcherCreate;
import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;
import com.chasermanager.domain.models.Switcher;
import com.chasermanager.exceptions.NotFoundException;
import com.chasermanager.exceptions.AlreadyExistsException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface SwitcherService {

    Switcher create(SwitcherCreate switcherCreate) throws NotFoundException, AlreadyExistsException;

    void setStatus(Long id, SwitcherStatus status);

    void update(Long id, Periodicity periodicity);

    List<Switcher> findAllByPeriodicity(long periodicity);

    void run(Switcher switcher) throws IOException, MessagingException;
}

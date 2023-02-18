package com.chasermanager.services;

import com.chasermanager.domain.models.Switcher;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;

import java.io.IOException;

public interface PreparationService {
    void preparePobeda63 (Switcher switcherm) throws IOException, MessagingException;
}

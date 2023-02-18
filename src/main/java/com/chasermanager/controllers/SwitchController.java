package com.chasermanager.controllers;

import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;

import com.chasermanager.services.SwitcherService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chasers")
@RequiredArgsConstructor
public class SwitchController {
    private final SwitcherService switcherService;

    @PostMapping
    public void create(@RequestParam String source,
                       @RequestParam String link,
                       @RequestParam Periodicity periodicity) {
        switcherService.create(source, link, periodicity);
    }

    @PostMapping(value = "/{id}")
    public void setStatus(@RequestParam SwitcherStatus status,
                          @PathVariable Long id) throws IOException, MessagingException {
        switcherService.setStatus(status, id);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Long id,
                       @RequestParam Periodicity periodicity) {
        switcherService.update(id, periodicity);
    }

}

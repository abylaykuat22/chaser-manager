package com.chasermanager.controllers;

import com.chasermanager.domain.dto.SwitcherCreate;
import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;
import com.chasermanager.domain.models.Switcher;
import com.chasermanager.exceptions.NotFoundException;
import com.chasermanager.exceptions.AlreadyExistsException;
import com.chasermanager.services.SwitcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chasers")
@RequiredArgsConstructor
public class SwitchController {
    private final SwitcherService switcherService;

    @PostMapping
    public Switcher create(@RequestBody SwitcherCreate switcherCreate) throws NotFoundException, AlreadyExistsException {
        return switcherService.create(switcherCreate);
    }

    @PutMapping(value = "/{id}/status")
    public void setStatus(@PathVariable Long id,
                          @RequestParam SwitcherStatus status) {
        switcherService.setStatus(id, status);
    }

    @PutMapping(value = "/{id}/periodicity")
    public void update(@PathVariable Long id,
                       @RequestParam Periodicity periodicity) {
        switcherService.update(id, periodicity);
    }

}

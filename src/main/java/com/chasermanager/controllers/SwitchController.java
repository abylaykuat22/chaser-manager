package com.chasermanager.controllers;

import com.chasermanager.domain.dto.SwitcherCreate;
import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;
import com.chasermanager.domain.models.Switcher;
import com.chasermanager.exceptions.AlreadyExistsException;
import com.chasermanager.exceptions.NotFoundException;
import com.chasermanager.services.SwitcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/chasers")
@RequiredArgsConstructor
public class SwitchController {
    private final SwitcherService switcherService;

    @GetMapping
    public Page<Switcher> findAll(@PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable) {
        return switcherService.findAllByCurrentUser(pageable);
    }

    @GetMapping(value = "/{id}")
    public Switcher findById(@PathVariable Long id) {
        return switcherService.findById(id);
    }

    @PostMapping
    public Switcher create(@RequestBody SwitcherCreate switcherCreate) throws NotFoundException, AlreadyExistsException {
        return switcherService.create(switcherCreate);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Long id,
                       @RequestParam(required = false) SwitcherStatus status,
                       @RequestParam(required = false) Periodicity periodicity) {
        switcherService.update(id, status, periodicity);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        switcherService.delete(id);
    }
}

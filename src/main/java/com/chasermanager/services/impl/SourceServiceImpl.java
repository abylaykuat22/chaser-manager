package com.chasermanager.services.impl;

import com.chasermanager.domain.models.Source;
import com.chasermanager.exceptions.NotFoundException;
import com.chasermanager.repositories.SourceRepository;
import com.chasermanager.services.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;

    @Override
    public Source findByName(String name) throws NotFoundException {
        Source source = sourceRepository.findByName(name);
        if (source==null) throw new NotFoundException("The source named "+name+" was not found");
        return source;
    }
}

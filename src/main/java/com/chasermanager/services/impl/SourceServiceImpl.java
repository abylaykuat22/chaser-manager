package com.chasermanager.services.impl;

import com.chasermanager.domain.models.Source;
import com.chasermanager.repositories.SourceRepository;
import com.chasermanager.repositories.UrlRepository;
import com.chasermanager.services.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;

    @Override
    public Source findByName(String name) {
        return sourceRepository.findByName(name);
    }

    @Override
    public boolean isExist(Source source) {
        return source != null;
    }
}

package com.chasermanager.services.impl;

import com.chasermanager.domain.models.Source;
import com.chasermanager.domain.models.Url;
import com.chasermanager.exceptions.NotFoundException;
import com.chasermanager.repositories.UrlRepository;
import com.chasermanager.services.SourceService;
import com.chasermanager.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final SourceService sourceService;

    @Override
    public Url create(String link, String sourceName) throws NotFoundException {
        Url url = urlRepository.findByLink(link);
        if (url != null) return url;
        Source source = sourceService.findByName(sourceName);
        Url newUrl = new Url();
        newUrl.setLink(link);
        newUrl.setSource(source);
        return urlRepository.save(newUrl);
    }
}

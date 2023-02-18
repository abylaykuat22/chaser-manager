package com.chasermanager.services.impl;

import com.chasermanager.domain.models.Source;
import com.chasermanager.domain.models.Url;
import com.chasermanager.repositories.UrlRepository;
import com.chasermanager.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Override
    public Url create(String link, Source source) {
        Url u = urlRepository.findByLink(link);
        if (!isExist(u) && u == null) {
            Url newUrl = new Url();
            newUrl.setLink(link);
            newUrl.setSource(source);
            return urlRepository.save(newUrl);
        }
        return u;
    }

    private boolean isExist(Url url) {
        List<Url> urls = urlRepository.findAll();
        return urls.contains(url);
    }

    @Override
    public Url findByUrlAndSource(String url, String sourceName) {
        return urlRepository.findByUrlAndSourceName(url, sourceName);
    }
}

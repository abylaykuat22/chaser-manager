package com.chasermanager.services;

import com.chasermanager.domain.models.Source;
import com.chasermanager.domain.models.Url;

public interface UrlService {
    Url create(String link, Source source);
    Url findByUrlAndSource(String url, String sourceName);
}

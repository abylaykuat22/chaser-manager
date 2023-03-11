package com.chasermanager.services;

import com.chasermanager.domain.models.Url;
import com.chasermanager.exceptions.NotFoundException;

public interface UrlService {
    Url create(String link, String sourceName) throws NotFoundException;

}

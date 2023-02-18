package com.chasermanager.services;

import com.chasermanager.domain.models.Source;
import com.chasermanager.domain.models.Url;

public interface SourceService {
    boolean isExist(Source name);
    Source findByName(String name);
}

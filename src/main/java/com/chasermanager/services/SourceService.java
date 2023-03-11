package com.chasermanager.services;

import com.chasermanager.domain.models.Source;
import com.chasermanager.exceptions.NotFoundException;

public interface SourceService {
    Source findByName(String name) throws NotFoundException;
}

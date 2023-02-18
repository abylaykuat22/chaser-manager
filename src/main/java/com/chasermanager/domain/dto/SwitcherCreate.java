package com.chasermanager.domain.dto;

import com.chasermanager.domain.models.Url;
import com.chasermanager.domain.models.User;
import lombok.Data;

@Data
public class SwitcherCreate {
    private User user;
    private Url link;
}

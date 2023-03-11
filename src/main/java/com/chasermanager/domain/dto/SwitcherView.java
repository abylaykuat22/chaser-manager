package com.chasermanager.domain.dto;

import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;
import lombok.Data;

@Data
public class SwitcherView {
    private Long id;
    private String url;
    private SwitcherStatus status;
    private Periodicity periodicity;
}

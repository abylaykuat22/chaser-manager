package com.chasermanager.domain.dto;

import com.chasermanager.domain.enums.Periodicity;
import lombok.Data;

@Data
public class SwitcherCreate {
    private String source;
    private String link;
    private Periodicity periodicity;
}

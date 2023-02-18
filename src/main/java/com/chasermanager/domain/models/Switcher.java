package com.chasermanager.domain.models;

import com.chasermanager.domain.enums.SwitcherStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "switchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Switcher extends BaseEntity{
    @ManyToOne
    private User user;

    @ManyToOne
    private Url url;

    @Enumerated(EnumType.STRING)
    private SwitcherStatus status;

    private long periodicity;


}

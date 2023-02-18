package com.chasermanager.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity{
    private String name;
    private String price;
    private String description;
    private String img;
    private String link;
    @ManyToOne
    private Source source;
    @ManyToOne
    private User user;
}

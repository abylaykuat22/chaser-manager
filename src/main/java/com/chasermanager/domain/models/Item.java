package com.chasermanager.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {
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

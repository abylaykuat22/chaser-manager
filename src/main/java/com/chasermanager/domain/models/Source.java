package com.chasermanager.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sources")
public class Source extends BaseEntity{
    private String name;

    public static String generateSubject(String name) {
        return switch (name) {
            case "Technodom" -> "В technodom появились новые объявления по вашему запросу";
            case "Sulpak" -> "В sulpak появились новые объявления по вашему запросу";
            default -> "В победа-63.рф появились новые объявления по вашему запросу";
        };
    }
}

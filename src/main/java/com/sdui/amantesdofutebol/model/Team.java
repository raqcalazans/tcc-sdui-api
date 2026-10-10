package com.sdui.amantesdofutebol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id
    @Column(length = 3)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

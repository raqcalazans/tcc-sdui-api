package com.selecaoglobocom.brasileirao_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 3, unique = true)
    private String acronym;

    @Column(name = "logo_url", nullable = false)
    private URL logoUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
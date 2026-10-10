package com.sdui.amantesdofutebol.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "football_fans")
@Getter
@Setter
@NoArgsConstructor
public class Fan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private java.time.LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "favorite_team_id", nullable = false)
    private Team favoriteTeam;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "stadium_frequency", nullable = false)
    private StadiumFrequency stadiumFrequency;
}


package com.sdui.brasileirao_api.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "fans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team favoriteTeam; 

    @Column(name = "subscription_plan", nullable = false)
    private String subscriptionPlan;

    @Column(name = "receives_alerts", nullable = false)
    private boolean receivesAlerts;
}


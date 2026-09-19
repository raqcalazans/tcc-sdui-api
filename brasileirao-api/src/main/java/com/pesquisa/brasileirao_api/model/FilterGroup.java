package com.pesquisa.brasileirao_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "filter_groups")
@Getter
@Setter
public class FilterGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private int displayOrder;

    @OneToMany(mappedBy = "filterGroup", fetch = FetchType.LAZY)
    private List<Game> games;
}
package com.sdui.amantesdofutebol.controller;

import com.sdui.amantesdofutebol.dto.TeamDTO;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/teams", "/api/v1/teams"})
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<TeamDTO> findAll() {
        return teamRepository.findAll().stream()
                .sorted((first, second) -> first.getName().compareToIgnoreCase(second.getName()))
                .map(team -> new TeamDTO(team.getId(), team.getName()))
                .toList();
    }
}

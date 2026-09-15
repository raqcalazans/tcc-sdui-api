package com.selecaoglobocom.brasileirao_api.controller;

import com.selecaoglobocom.brasileirao_api.dto.TeamDTO;
import com.selecaoglobocom.brasileirao_api.service.TeamService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "*")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.findAllTeams();
    }
}
package com.sdui.brasileirao_api.service;

import com.sdui.brasileirao_api.dto.TeamDTO;
import com.sdui.brasileirao_api.mapper.TeamMapper;
import com.sdui.brasileirao_api.model.Team;
import com.sdui.brasileirao_api.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public List<TeamDTO> findAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teamMapper.toDto(teams);
    }
}

package com.selecaoglobocom.brasileirao_api.service;

import com.selecaoglobocom.brasileirao_api.dto.TeamDTO;
import com.selecaoglobocom.brasileirao_api.entity.Team;
import com.selecaoglobocom.brasileirao_api.mapper.TeamMapper;
import com.selecaoglobocom.brasileirao_api.repository.TeamRepository;
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

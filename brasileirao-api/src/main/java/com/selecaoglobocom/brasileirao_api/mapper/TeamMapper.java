package com.selecaoglobocom.brasileirao_api.mapper;

import com.selecaoglobocom.brasileirao_api.dto.TeamDTO;
import com.selecaoglobocom.brasileirao_api.entity.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public TeamDTO toDto(Team team) {
        if (team == null) {
            return null;
        }
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setAcronym(team.getAcronym());
        dto.setLogoURL(team.getLogoUrl()); 
        dto.setDescription(team.getDescription());
        
        return dto;
    }

    public List<TeamDTO> toDto(List<Team> teams) {
        return teams.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

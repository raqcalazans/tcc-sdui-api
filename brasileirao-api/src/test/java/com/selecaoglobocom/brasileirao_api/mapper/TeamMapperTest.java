package com.selecaoglobocom.brasileirao_api.mapper;

import com.selecaoglobocom.brasileirao_api.dto.TeamDTO;
import com.selecaoglobocom.brasileirao_api.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamMapperTest {

    private TeamMapper teamMapper;

    @BeforeEach
    void setUp() {
        this.teamMapper = new TeamMapper();
    }
    
    @Test
    void shouldMapTeamEntityToDTO() {
        // Arrange
        Team team = new Team();
        team.setId(1);
        team.setName("Flamengo");
        team.setAcronym("FLA");

        // Act
        TeamDTO teamDTO = teamMapper.toDto(team);

        // Assert
        assertNotNull(teamDTO);
        assertEquals(1, teamDTO.getId());
        assertEquals("Flamengo", teamDTO.getName());
        assertEquals("FLA", teamDTO.getAcronym());
    }
    
}

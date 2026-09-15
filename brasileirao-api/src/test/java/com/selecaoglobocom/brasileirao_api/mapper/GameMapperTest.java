package com.selecaoglobocom.brasileirao_api.mapper;

import com.selecaoglobocom.brasileirao_api.dto.GameDTO;
import com.selecaoglobocom.brasileirao_api.entity.FilterGroup;
import com.selecaoglobocom.brasileirao_api.entity.Game;
import com.selecaoglobocom.brasileirao_api.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapperTest {

    private GameMapper gameMapper;

    @BeforeEach
    void setUp() {
        TeamMapper teamMapper = new TeamMapper();
        this.gameMapper = new GameMapper(teamMapper);
    }

    @Test
    void shouldMapGameEntityToDTOAndSetIsLiveToTrue_whenGroupIsLive() {
        // Arrange
        Team homeTeam = new Team();
        homeTeam.setId(1);
        homeTeam.setName("Flamengo");
        homeTeam.setAcronym("FLA");

        Team awayTeam = new Team();
        awayTeam.setId(2); 
        awayTeam.setName("Vasco da Gama");
        awayTeam.setAcronym("VAS");

        FilterGroup liveGroup = new FilterGroup();
        liveGroup.setTitle("AO VIVO");

        Game gameEntity = new Game();
        gameEntity.setId(101);
        gameEntity.setHomeTeam(homeTeam);
        gameEntity.setAwayTeam(awayTeam);
        gameEntity.setFilterGroup(liveGroup);
        gameEntity.setLive(true);

        // Act
        GameDTO gameDTO = gameMapper.toDto(gameEntity);

        // Assert
        assertNotNull(gameDTO);
        assertTrue(gameDTO.isLive());
        assertEquals(1, gameDTO.getHomeTeam().getId()); 
    }

    @Test
    void shouldMapGameEntityToDTOAndSetIsLiveToFalse_whenGroupIsNotLive() {
        // Arrange
        Team homeTeam = new Team();
        homeTeam.setId(3); 
        homeTeam.setName("Palmeiras");
        homeTeam.setAcronym("PAL");

        Team awayTeam = new Team();
        awayTeam.setId(4);
        awayTeam.setName("Corinthians");
        awayTeam.setAcronym("COR");

        FilterGroup finishedGroup = new FilterGroup();
        finishedGroup.setTitle("ENCERRADOS");

        Game gameEntity = new Game();
        gameEntity.setId(102);
        gameEntity.setHomeTeam(homeTeam);
        gameEntity.setAwayTeam(awayTeam);
        gameEntity.setFilterGroup(finishedGroup);
        gameEntity.setLive(false);

        // Act
        GameDTO gameDTO = gameMapper.toDto(gameEntity);

        // Assert
        assertNotNull(gameDTO);
        assertFalse(gameDTO.isLive());
        assertEquals(4, gameDTO.getAwayTeam().getId()); 
    }
}
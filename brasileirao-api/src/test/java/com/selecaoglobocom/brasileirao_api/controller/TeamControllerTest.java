package com.selecaoglobocom.brasileirao_api.controller;

import com.selecaoglobocom.brasileirao_api.dto.TeamDTO;
import com.selecaoglobocom.brasileirao_api.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    void testGetAllTeams() throws Exception {
        // Arrange
        TeamDTO mockTeam = new TeamDTO(1, "Flamengo", "FLA", new URL("http://s.glbimg.com/es/sde/f/equipes/2014/04/14/flamengo_60x60.png"), "Time do RJ");

        when(teamService.findAllTeams()).thenReturn(Collections.singletonList(mockTeam));

        // Act & Assert
        mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].name").isString())
                .andExpect(jsonPath("$.[0].acronym").isString())
                .andExpect(jsonPath("$.[0].description").isString())
                .andExpect(jsonPath("$.[0].logoURL").isString());
    }
}
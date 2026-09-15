package com.selecaoglobocom.brasileirao_api.controller;

import com.selecaoglobocom.brasileirao_api.dto.GameGroupDTO;
import com.selecaoglobocom.brasileirao_api.dto.GameScreenDTO;
import com.selecaoglobocom.brasileirao_api.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    void shouldReturnGameScreenDTO_whenGetGamesEndpointIsCalled() throws Exception {
        // Arrange
        GameGroupDTO mockGroup = new GameGroupDTO("1ª RODADA", Collections.emptyList());
        GameScreenDTO mockScreen = new GameScreenDTO("JOGOS", Collections.singletonList(mockGroup));

        when(gameService.findAllGamesGrouped()).thenReturn(mockScreen);

        // Act & Assert
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk()) 
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(jsonPath("$.screen_title").value("JOGOS")) 
                .andExpect(jsonPath("$.groups[0].title").value("1ª RODADA")); 
    }
}
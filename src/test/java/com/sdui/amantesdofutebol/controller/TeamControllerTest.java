package com.sdui.amantesdofutebol.controller;

import com.sdui.amantesdofutebol.dto.TeamDTO;
import com.sdui.amantesdofutebol.model.Team;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock private TeamRepository teamRepository;

    @Test
    void findAllReturnsAlphabeticallySortedTeamOptions() {
        when(teamRepository.findAll()).thenReturn(List.of(
                new Team("FLA", "Flamengo"),
                new Team("CAP", "Athletico-PR"),
                new Team("CAM", "Atlético-MG")
        ));

        List<TeamDTO> result = new TeamController(teamRepository).findAll();

        assertEquals(List.of("Athletico-PR", "Atlético-MG", "Flamengo"),
                result.stream().map(TeamDTO::getName).toList());
        assertEquals(List.of("CAP", "CAM", "FLA"), result.stream().map(TeamDTO::getId).toList());
    }
}

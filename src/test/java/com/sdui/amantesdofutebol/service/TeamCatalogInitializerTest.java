package com.sdui.amantesdofutebol.service;

import com.sdui.amantesdofutebol.model.Team;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamCatalogInitializerTest {

    @Mock private TeamRepository teamRepository;

    @Test
    void seedsAll2026SerieATeamsWhenCatalogIsEmpty() throws Exception {
        when(teamRepository.existsById(anyString())).thenReturn(false);

        new TeamCatalogInitializer(teamRepository).run(null);

        ArgumentCaptor<Team> captor = ArgumentCaptor.forClass(Team.class);
        verify(teamRepository, times(20)).save(captor.capture());
        List<String> ids = captor.getAllValues().stream().map(Team::getId).toList();
        assertEquals(20, ids.size());
        assertEquals(20, ids.stream().distinct().count());
        verify(teamRepository, times(20)).existsById(anyString());
    }

    @Test
    void doesNotDuplicateTeamsAlreadyInCatalog() throws Exception {
        when(teamRepository.existsById(anyString())).thenReturn(true);

        new TeamCatalogInitializer(teamRepository).run(null);

        verify(teamRepository, times(20)).existsById(anyString());
        verify(teamRepository, never()).save(any(Team.class));
    }
}

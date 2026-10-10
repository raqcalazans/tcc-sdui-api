package com.sdui.amantesdofutebol.service;

import com.sdui.amantesdofutebol.dto.FanDTO;
import com.sdui.amantesdofutebol.model.Fan;
import com.sdui.amantesdofutebol.model.StadiumFrequency;
import com.sdui.amantesdofutebol.model.Team;
import com.sdui.amantesdofutebol.repository.FanRepository;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FanServiceTest {

    @Mock private FanRepository fanRepository;
    @Mock private TeamRepository teamRepository;

    private FanService service;
    private Team team;

    @BeforeEach
    void setUp() {
        service = new FanService(fanRepository, teamRepository);
        team = new Team("FLA", "Flamengo");
    }

    @Test
    void findAllMapsEveryFanToDto() {
        Fan fan = fan(1L, "Ana", "ana@example.com");
        when(fanRepository.findAll()).thenReturn(List.of(fan));

        List<FanDTO> result = service.findAll();

        assertEquals(1, result.size());
        assertDto(result.get(0), fan);
    }

    @Test
    void findAllReturnsEmptyListWhenRepositoryIsEmpty() {
        when(fanRepository.findAll()).thenReturn(List.of());

        assertTrue(service.findAll().isEmpty());
    }

    @Test
    void findByIdReturnsFanDto() {
        Fan fan = fan(2L, "Bruno", "bruno@example.com");
        when(fanRepository.findById(2L)).thenReturn(Optional.of(fan));

        assertDto(service.findById(2L), fan);
    }

    @Test
    void findByIdThrowsNotFoundWhenFanDoesNotExist() {
        when(fanRepository.findById(88L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.findById(88L));

        assertEquals(404, exception.getStatusCode().value());
    }

    @Test
    void createTrimsFieldsAndReturnsSavedFan() {
        FanDTO input = dto();
        when(fanRepository.findByEmailIgnoreCase(input.getEmail().trim())).thenReturn(Optional.empty());
        when(teamRepository.findById("FLA")).thenReturn(Optional.of(team));
        when(fanRepository.save(any(Fan.class))).thenAnswer(invocation -> {
            Fan saved = invocation.getArgument(0);
            saved.setId(10L);
            return saved;
        });

        FanDTO result = service.create(input);

        ArgumentCaptor<Fan> captor = ArgumentCaptor.forClass(Fan.class);
        verify(fanRepository).save(captor.capture());
        assertEquals("Ana Silva", captor.getValue().getName());
        assertEquals("ana@example.com", captor.getValue().getEmail());
        assertEquals(team, captor.getValue().getFavoriteTeam());
        assertEquals(10L, result.getId());
        assertEquals("FLA", result.getFavoriteTeamId());
        assertEquals(LocalDate.of(1995, 4, 20), result.getBirthDate());
    }

    @Test
    void createRejectsDuplicateEmail() {
        FanDTO input = dto();
        when(fanRepository.findByEmailIgnoreCase(input.getEmail().trim()))
                .thenReturn(Optional.of(fan(1L, "Someone", input.getEmail())));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.create(input));

        assertEquals(409, exception.getStatusCode().value());
        verifyNoInteractions(teamRepository);
        verify(fanRepository, never()).save(any());
    }

    @Test
    void createRejectsUnknownFavoriteTeam() {
        FanDTO input = dto();
        when(fanRepository.findByEmailIgnoreCase(input.getEmail().trim())).thenReturn(Optional.empty());
        when(teamRepository.findById("FLA")).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.create(input));

        assertEquals(400, exception.getStatusCode().value());
        verify(fanRepository, never()).save(any());
    }

    @Test
    void updatePreservesSameFanEmailAndUpdatesFields() {
        Fan existing = fan(3L, "Old name", "ana@example.com");
        FanDTO input = dto();
        when(fanRepository.findById(3L)).thenReturn(Optional.of(existing));
        when(fanRepository.findByEmailIgnoreCase(input.getEmail().trim())).thenReturn(Optional.of(existing));
        when(teamRepository.findById("FLA")).thenReturn(Optional.of(team));
        when(fanRepository.save(any(Fan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        FanDTO result = service.update(3L, input);

        assertDto(result, existing);
        assertEquals("Ana Silva", existing.getName());
        verify(fanRepository).save(existing);
    }

    @Test
    void updateRejectsEmailOwnedByAnotherFan() {
        FanDTO input = dto();
        when(fanRepository.findById(3L)).thenReturn(Optional.of(fan(3L, "Existing", "existing@example.com")));
        when(fanRepository.findByEmailIgnoreCase(input.getEmail().trim()))
                .thenReturn(Optional.of(fan(4L, "Another", input.getEmail())));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.update(3L, input));

        assertEquals(409, exception.getStatusCode().value());
        verifyNoInteractions(teamRepository);
        verify(fanRepository, never()).save(any());
    }

    @Test
    void updateThrowsNotFoundForMissingFan() {
        when(fanRepository.findById(9L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.update(9L, dto()));

        assertEquals(404, exception.getStatusCode().value());
        verify(fanRepository, never()).save(any());
    }

    @Test
    void deleteRemovesExistingFan() {
        Fan fan = fan(5L, "Ana", "ana@example.com");
        when(fanRepository.findById(5L)).thenReturn(Optional.of(fan));

        service.delete(5L);

        verify(fanRepository).delete(fan);
    }

    @Test
    void deleteThrowsNotFoundForMissingFan() {
        when(fanRepository.findById(5L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.delete(5L));

        assertEquals(404, exception.getStatusCode().value());
        verify(fanRepository, never()).delete(any());
    }

    private FanDTO dto() {
        FanDTO dto = new FanDTO();
        dto.setName("  Ana Silva  ");
        dto.setEmail("  ana@example.com  ");
        dto.setBirthDate(LocalDate.of(1995, 4, 20));
        dto.setGender("  Feminino  ");
        dto.setFavoriteTeamId("FLA");
        dto.setCity("  Rio de Janeiro  ");
        dto.setState(" RJ ");
        dto.setCountry(" Brasil ");
        dto.setStadiumFrequency(StadiumFrequency.AS_VEZES);
        return dto;
    }

    private Fan fan(Long id, String name, String email) {
        Fan fan = new Fan();
        fan.setId(id);
        fan.setName(name);
        fan.setEmail(email);
        fan.setBirthDate(LocalDate.of(1995, 4, 20));
        fan.setGender("Feminino");
        fan.setFavoriteTeam(team);
        fan.setCity("Rio de Janeiro");
        fan.setState("RJ");
        fan.setCountry("Brasil");
        fan.setStadiumFrequency(StadiumFrequency.AS_VEZES);
        return fan;
    }

    private void assertDto(FanDTO actual, Fan expected) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
        assertEquals(expected.getGender(), actual.getGender());
        assertEquals(expected.getFavoriteTeam().getId(), actual.getFavoriteTeamId());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getState(), actual.getState());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getStadiumFrequency(), actual.getStadiumFrequency());
    }
}

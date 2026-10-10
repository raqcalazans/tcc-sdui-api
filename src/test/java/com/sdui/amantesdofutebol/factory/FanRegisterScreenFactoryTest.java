package com.sdui.amantesdofutebol.factory;

import com.sdui.amantesdofutebol.dto.sdui.ComponentDTO;
import com.sdui.amantesdofutebol.dto.sdui.FormScreenDTO;
import com.sdui.amantesdofutebol.model.Team;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FanRegisterScreenFactoryTest {

    @Mock private TeamRepository teamRepository;

    @Test
    void buildsScreenWithAllResearchFieldsAndOptions() {
        when(teamRepository.findAll()).thenReturn(List.of(
                new Team("FLA", "Flamengo"),
                new Team("CAP", "Athletico-PR")
        ));

        FormScreenDTO screen = new FanRegisterScreenFactory(teamRepository).buildRegistrationScreen();

        assertEquals("Cadastro de fã de futebol", screen.getScreenTitle());
        assertEquals("Finalizar cadastro", screen.getSubmitButton().getText());
        assertEquals("/fans", screen.getSubmitButton().getUrl());
        assertEquals(List.of("name", "email", "birthDate", "gender", "favoriteTeamId", "city", "state",
                        "country", "stadiumFrequency"),
                screen.getComponents().stream().map(ComponentDTO::getId).toList());

        ComponentDTO teamField = findField(screen, "favoriteTeamId");
        assertEquals("DROP_DOWN", teamField.getType());
        assertEquals(List.of("CAP", "FLA"), teamField.getOptions().stream().map(option -> option.getId()).toList());
        assertEquals(List.of("Athletico-PR", "Flamengo"),
                teamField.getOptions().stream().map(option -> option.getLabel()).toList());

        ComponentDTO stadiumField = findField(screen, "stadiumFrequency");
        assertEquals("DROP_DOWN", stadiumField.getType());
        assertEquals(List.of("SEMPRE", "AS_VEZES", "RARAMENTE", "NUNCA"),
                stadiumField.getOptions().stream().map(option -> option.getId()).toList());
        assertEquals(List.of("Sempre", "Às vezes", "Raramente", "Nunca"),
                stadiumField.getOptions().stream().map(option -> option.getLabel()).toList());
    }

    @Test
    void buildsEmptyTeamChoiceWhenCatalogIsEmpty() {
        when(teamRepository.findAll()).thenReturn(List.of());

        FormScreenDTO screen = new FanRegisterScreenFactory(teamRepository).buildRegistrationScreen();

        assertTrue(findField(screen, "favoriteTeamId").getOptions().isEmpty());
    }

    private ComponentDTO findField(FormScreenDTO screen, String id) {
        return screen.getComponents().stream().filter(component -> component.getId().equals(id)).findFirst().orElseThrow();
    }
}

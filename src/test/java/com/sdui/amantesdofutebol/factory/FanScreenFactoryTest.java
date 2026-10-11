package com.sdui.amantesdofutebol.factory;

import com.sdui.amantesdofutebol.dto.sdui.ScreenComponentDTO;
import com.sdui.amantesdofutebol.dto.sdui.ScreenDefinitionDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FanScreenFactoryTest {

    private final FanScreenFactory factory = new FanScreenFactory();

    @Test
    void buildsRegistrationScreenWithSharedFanFieldsAndDynamicTeams() {
        ScreenDefinitionDTO screen = factory.buildRegistrationScreen();

        assertEquals("fan-registration", screen.getScreenId());
        assertEquals("FORM", screen.getScreenType());
        assertEquals("Cadastro de fã", screen.getScreenTitle());
        assertEquals(List.of("personalData", "preferences"),
                screen.getSections().stream().map(section -> section.getId()).toList());
        assertEquals(List.of("name", "email", "birthDate", "gender", "favoriteTeamId", "city", "state",
                        "country", "stadiumFrequency"), flatten(screen).stream().map(ScreenComponentDTO::getField).toList());

        ScreenComponentDTO birthDate = component(screen, "birthDate");
        assertEquals("DATE_FIELD", birthDate.getType());
        assertEquals("dd/MM/yyyy", birthDate.getFormat());

        ScreenComponentDTO gender = component(screen, "gender");
        assertEquals(List.of("Feminino", "Masculino", "Não Binário"),
                gender.getOptions().stream().map(option -> option.getLabel()).toList());

        ScreenComponentDTO team = component(screen, "favoriteTeamId");
        assertEquals("/api/v1/teams", team.getOptionsSource().getPath());
        assertEquals("id", team.getOptionsSource().getValueField());
        assertEquals("name", team.getOptionsSource().getLabelField());

        ScreenComponentDTO frequency = component(screen, "stadiumFrequency");
        assertEquals(List.of("SEMPRE", "AS_VEZES", "RARAMENTE", "NUNCA"),
                frequency.getOptions().stream().map(option -> option.getId()).toList());
    }

    @Test
    void buildsListDetailAndEditScreens() {
        assertEquals("LIST", factory.buildListScreen().getScreenType());
        assertEquals("DETAIL", factory.buildDetailScreen().getScreenType());
        assertEquals("FORM", factory.buildEditScreen().getScreenType());
        assertEquals("saveChanges", factory.buildEditScreen().getActions().get(0).getId());
        var detailScreen = factory.buildDetailScreen();
        int deleteActionIndex = detailScreen.getActions().size() - 1;
        assertTrue(detailScreen.getActions().get(deleteActionIndex).getRequiresConfirmation());
    }

    private List<ScreenComponentDTO> flatten(ScreenDefinitionDTO screen) {
        return screen.getSections().stream().flatMap(section -> section.getComponents().stream()).toList();
    }

    private ScreenComponentDTO component(ScreenDefinitionDTO screen, String id) {
        return flatten(screen).stream().filter(component -> component.getId().equals(id)).findFirst().orElseThrow();
    }
}

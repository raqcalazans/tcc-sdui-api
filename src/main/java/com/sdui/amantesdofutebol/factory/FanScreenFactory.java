package com.sdui.amantesdofutebol.factory;

import com.sdui.amantesdofutebol.dto.sdui.OptionDTO;
import com.sdui.amantesdofutebol.dto.sdui.OptionsSourceDTO;
import com.sdui.amantesdofutebol.dto.sdui.ScreenActionDTO;
import com.sdui.amantesdofutebol.dto.sdui.ScreenComponentDTO;
import com.sdui.amantesdofutebol.dto.sdui.ScreenDefinitionDTO;
import com.sdui.amantesdofutebol.dto.sdui.ScreenSectionDTO;
import com.sdui.amantesdofutebol.model.StadiumFrequency;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FanScreenFactory {

    public ScreenDefinitionDTO buildListScreen() {
        return ScreenDefinitionDTO.builder()
                .screenId("fan-list")
                .screenTitle("Amantes do Futebol")
                .screenType("LIST")
                .sections(List.of(ScreenSectionDTO.builder()
                        .id("fans")
                        .components(List.of(ScreenComponentDTO.builder()
                                .id("fanList")
                                .type("FAN_LIST")
                                .titleField("name")
                                .subtitleField("favoriteTeamId")
                                .subtitleOptionsSource(teamOptionsSource())
                                .selectAction("openFanDetails")
                                .build()))
                        .build()))
                .actions(List.of(action("createFan", "Adicionar fã", "NAVIGATE")))
                .build();
    }

    public ScreenDefinitionDTO buildRegistrationScreen() {
        return buildFanForm("fan-registration", "Cadastro de fã", "submit", "Cadastrar fã");
    }

    public ScreenDefinitionDTO buildEditScreen() {
        return buildFanForm("fan-edit", "Editar fã", "saveChanges", "Salvar alterações");
    }

    public ScreenDefinitionDTO buildDetailScreen() {
        List<ScreenComponentDTO> personalData = List.of(
                value("name", "Nome"),
                value("email", "E-mail"),
                value("birthDate", "Data de nascimento", "dd/MM/yyyy"),
                value("age", "Idade", "AGE"),
                value("gender", "Gênero")
        );
        List<ScreenComponentDTO> preferences = List.of(
                ScreenComponentDTO.builder().id("favoriteTeamId").type("VALUE")
                        .field("favoriteTeamId").label("Time favorito")
                        .optionsSource(teamOptionsSource()).build(),
                ScreenComponentDTO.builder().id("location").type("VALUE")
                        .fields(List.of("city", "state", "country"))
                        .label("Localização").format("LOCATION").build(),
                value("stadiumFrequency", "Frequência no estádio")
        );

        return ScreenDefinitionDTO.builder()
                .screenId("fan-detail")
                .screenTitle("Detalhes do fã")
                .screenType("DETAIL")
                .sections(List.of(
                        section("personalData", "Dados pessoais", personalData),
                        section("preferences", "Preferências e rotina", preferences)))
                .actions(List.of(
                        action("editFan", "Editar", "NAVIGATE"),
                        ScreenActionDTO.builder().id("deleteFan").title("Excluir")
                                .role("DELETE").requiresConfirmation(true).build()))
                .build();
    }

    private ScreenDefinitionDTO buildFanForm(String screenId, String title, String actionId, String actionTitle) {
        List<ScreenComponentDTO> personalData = List.of(
                textField("name", "name", "Nome", null),
                textField("email", "email", "E-mail", "EMAIL"),
                ScreenComponentDTO.builder().id("birthDate").type("DATE_FIELD")
                        .field("birthDate").label("Data de nascimento").required(true)
                        .format("dd/MM/yyyy").build(),
                select("gender", "Gênero", List.of(
                        option("Feminino", "Feminino"),
                        option("Masculino", "Masculino"),
                        option("Não Binário", "Não Binário")))
        );
        List<ScreenComponentDTO> preferences = List.of(
                ScreenComponentDTO.builder().id("favoriteTeamId").type("SELECT")
                        .field("favoriteTeamId").label("Time favorito").required(true)
                        .optionsSource(teamOptionsSource()).build(),
                textField("city", "city", "Cidade", null),
                textField("state", "state", "Estado", null),
                textField("country", "country", "País", null),
                select("stadiumFrequency", "Frequência no estádio", Arrays.stream(StadiumFrequency.values())
                        .map(frequency -> option(frequency.name(), frequencyLabel(frequency)))
                        .toList())
        );

        return ScreenDefinitionDTO.builder()
                .screenId(screenId)
                .screenTitle(title)
                .screenType("FORM")
                .sections(List.of(
                        section("personalData", "Dados pessoais", personalData),
                        section("preferences", "Preferências e rotina", preferences)))
                .actions(List.of(action(actionId, actionTitle, "SUBMIT")))
                .build();
    }

    private ScreenSectionDTO section(String id, String title, List<ScreenComponentDTO> components) {
        return ScreenSectionDTO.builder().id(id).title(title).components(components).build();
    }

    private ScreenComponentDTO textField(String id, String field, String label, String keyboard) {
        return ScreenComponentDTO.builder().id(id).type("TEXT_FIELD").field(field)
                .label(label).required(true).keyboard(keyboard).build();
    }

    private ScreenComponentDTO select(String field, String label, List<OptionDTO> options) {
        return ScreenComponentDTO.builder().id(field).type("SELECT").field(field)
                .label(label).required(true).options(options).build();
    }

    private ScreenComponentDTO value(String field, String label) {
        return value(field, label, null);
    }

    private ScreenComponentDTO value(String field, String label, String format) {
        return ScreenComponentDTO.builder().id(field).type("VALUE").field(field)
                .label(label).format(format).build();
    }

    private ScreenActionDTO action(String id, String title, String role) {
        return ScreenActionDTO.builder().id(id).title(title).role(role).build();
    }

    private OptionDTO option(String id, String label) {
        return OptionDTO.builder().id(id).label(label).build();
    }

    private OptionsSourceDTO teamOptionsSource() {
        return OptionsSourceDTO.builder().method("GET").path("/api/v1/teams")
                .valueField("id").labelField("name").build();
    }

    private String frequencyLabel(StadiumFrequency frequency) {
        return switch (frequency) {
            case SEMPRE -> "Sempre";
            case AS_VEZES -> "Às vezes";
            case RARAMENTE -> "Raramente";
            case NUNCA -> "Nunca";
        };
    }
}

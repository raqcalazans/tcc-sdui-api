package com.sdui.amantesdofutebol.factory;

import com.sdui.amantesdofutebol.dto.sdui.ButtonDTO;
import com.sdui.amantesdofutebol.dto.sdui.ComponentDTO;
import com.sdui.amantesdofutebol.dto.sdui.FormScreenDTO;
import com.sdui.amantesdofutebol.dto.sdui.OptionDTO;
import com.sdui.amantesdofutebol.model.StadiumFrequency;
import com.sdui.amantesdofutebol.model.Team;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FanRegisterScreenFactory {

    private final TeamRepository teamRepository;

    public FanRegisterScreenFactory(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public FormScreenDTO buildRegistrationScreen() {
        List<OptionDTO> teamOptions = teamRepository.findAll().stream()
                .sorted((first, second) -> first.getName().compareToIgnoreCase(second.getName()))
                .map(team -> OptionDTO.builder().id(team.getId()).label(team.getName()).build())
                .toList();

        List<OptionDTO> frequencyOptions = Arrays.stream(StadiumFrequency.values())
                .map(frequency -> OptionDTO.builder()
                        .id(frequency.name())
                        .label(switch (frequency) {
                            case SEMPRE -> "Sempre";
                            case AS_VEZES -> "Às vezes";
                            case RARAMENTE -> "Raramente";
                            case NUNCA -> "Nunca";
                        })
                        .build())
                .toList();

        List<ComponentDTO> components = List.of(
                textField("name", "Nome completo"),
                textField("email", "E-mail"),
                textField("birthDate", "Data de nascimento (AAAA-MM-DD)"),
                textField("gender", "Gênero"),
                ComponentDTO.builder().id("favoriteTeamId").type("DROP_DOWN")
                        .label("Time favorito").required(true).options(teamOptions).build(),
                textField("city", "Cidade"),
                textField("state", "Estado"),
                textField("country", "País"),
                ComponentDTO.builder().id("stadiumFrequency").type("DROP_DOWN")
                        .label("Frequência no estádio").required(true).options(frequencyOptions).build()
        );

        return FormScreenDTO.builder()
                .screenTitle("Cadastro de fã de futebol")
                .components(components)
                .submitButton(ButtonDTO.builder()
                        .text("Finalizar cadastro")
                        .url("/fans")
                        .build())
                .build();
    }

    private ComponentDTO textField(String id, String label) {
        return ComponentDTO.builder().id(id).type("TEXT_FIELD").label(label).required(true).build();
    }
}

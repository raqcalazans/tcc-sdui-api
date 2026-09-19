package com.pesquisa.brasileirao_api.factory;

import com.pesquisa.brasileirao_api.dto.sdui.OptionDTO;
import com.pesquisa.brasileirao_api.dto.sdui.ButtonDTO;
import com.pesquisa.brasileirao_api.dto.sdui.ComponentDTO;
import com.pesquisa.brasileirao_api.dto.sdui.FormScreenDTO;
import com.pesquisa.brasileirao_api.model.Team;
import com.pesquisa.brasileirao_api.repository.TeamRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FanRegisterScreenFactory {

    private final TeamRepository teamRepository;

    public FanRegisterScreenFactory(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public FormScreenDTO buildRegistrationScreen() {
        // 1. Busca os times no banco de dados para popular o DropDownList
        List<Team> teams = teamRepository.findAll();
        List<OptionDTO> teamOptions = teams.stream()
                .map(team -> OptionDTO.builder()
                        .id(team.getId().toString())
                        .label(team.getName())
                        .build())
                .collect(Collectors.toList());

        // 2. Constrói as opções hardcoded para os Radio Buttons de plano
        List<OptionDTO> planOptions = List.of(
                OptionDTO.builder().id("BRONZE").label("Plano Bronze").build(),
                OptionDTO.builder().id("PRATA").label("Plano Prata").build(),
                OptionDTO.builder().id("OURO").label("Plano Ouro").build()
        );

        // 3. Monta a árvore de componentes visuais da tela
        List<ComponentDTO> components = List.of(
                ComponentDTO.builder()
                        .id("name")
                        .type("TEXT_FIELD")
                        .label("Nome Completo")
                        .required(true)
                        .build(),
                
                ComponentDTO.builder()
                        .id("email")
                        .type("TEXT_FIELD")
                        .label("E-mail")
                        .required(true)
                        .build(),
                
                ComponentDTO.builder()
                        .id("favoriteTeamId")
                        .type("DROP_DOWN")
                        .label("Time do Coração")
                        .required(true)
                        .options(teamOptions)
                        .build(),
                
                ComponentDTO.builder()
                        .id("subscriptionPlan")
                        .type("RADIO_GROUP")
                        .label("Nível do Sócio Torcedor")
                        .required(true)
                        .options(planOptions)
                        .build(),
                
                ComponentDTO.builder()
                        .id("receivesAlerts")
                        .type("TOGGLE")
                        .label("Receber alertas de gols")
                        .required(false)
                        .build()
        );

        // 4. Cria o botão usando o Builder dele
        ButtonDTO submitBtn = ButtonDTO.builder()
                .text("Finalizar e Cadastrar Adepto")
                .url("/fans")
                .build();

        // 5. Retorna o contrato completo da tela para o iOS renderizar
        return FormScreenDTO.builder()
                .screenTitle("Cadastro de Sócio Torcedor")
                .components(components)
                .submitButton(submitBtn)
                .build();
    }
}
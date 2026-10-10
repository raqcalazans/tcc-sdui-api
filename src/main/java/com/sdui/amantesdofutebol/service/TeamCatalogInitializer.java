package com.sdui.amantesdofutebol.service;

import com.sdui.amantesdofutebol.model.Team;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamCatalogInitializer implements ApplicationRunner {

    private final TeamRepository teamRepository;

    public TeamCatalogInitializer(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Team> serieA2026 = List.of(
                new Team("CAP", "Athletico-PR"),
                new Team("CAM", "Atlético-MG"),
                new Team("BAH", "Bahia"),
                new Team("BOT", "Botafogo"),
                new Team("CHA", "Chapecoense"),
                new Team("COR", "Corinthians"),
                new Team("CFC", "Coritiba"),
                new Team("CRU", "Cruzeiro"),
                new Team("FLA", "Flamengo"),
                new Team("FLU", "Fluminense"),
                new Team("GRE", "Grêmio"),
                new Team("INT", "Internacional"),
                new Team("MIR", "Mirassol"),
                new Team("PAL", "Palmeiras"),
                new Team("RBB", "Red Bull Bragantino"),
                new Team("REM", "Remo"),
                new Team("SAN", "Santos"),
                new Team("SAO", "São Paulo"),
                new Team("VAS", "Vasco da Gama"),
                new Team("VIT", "Vitória")
        );

        serieA2026.stream()
                .filter(team -> !teamRepository.existsById(team.getId()))
                .forEach(teamRepository::save);
    }
}

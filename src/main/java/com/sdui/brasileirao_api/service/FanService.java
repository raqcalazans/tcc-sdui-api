package com.sdui.brasileirao_api.service;

import com.sdui.brasileirao_api.dto.FanDTO;
import com.sdui.brasileirao_api.model.Fan;
import com.sdui.brasileirao_api.model.Team;
import com.sdui.brasileirao_api.repository.FanRepository;
import com.sdui.brasileirao_api.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FanService {

    private final FanRepository fanRepository;
    private final TeamRepository teamRepository;

    public FanService(FanRepository fanRepository, TeamRepository teamRepository) {
        this.fanRepository = fanRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Fan saveFan(FanDTO fanDTO) {
        // 1. Busca a equipa na base de dados para garantir que a relação é válida
        Team favoriteTeam = teamRepository.findById(fanDTO.getFavoriteTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada na base de dados."));
        // 2. Converte o DTO de entrada para a Entidade gerida pelo JPA
        Fan fan = new Fan();
        fan.setName(fanDTO.getName());
        fan.setEmail(fanDTO.getEmail());
        fan.setFavoriteTeam(favoriteTeam);
        fan.setSubscriptionPlan(fanDTO.getSubscriptionPlan());
        fan.setReceivesAlerts(fanDTO.isReceivesAlerts());
        // 3. Guarda o novo adepto/sócio na base de dados
        return fanRepository.save(fan);
    }
}
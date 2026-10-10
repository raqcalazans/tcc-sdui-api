package com.sdui.amantesdofutebol.service;

import com.sdui.amantesdofutebol.dto.FanDTO;
import com.sdui.amantesdofutebol.model.Fan;
import com.sdui.amantesdofutebol.model.Team;
import com.sdui.amantesdofutebol.repository.FanRepository;
import com.sdui.amantesdofutebol.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FanService {

    private final FanRepository fanRepository;
    private final TeamRepository teamRepository;

    public FanService(FanRepository fanRepository, TeamRepository teamRepository) {
        this.fanRepository = fanRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public List<FanDTO> findAll() {
        return fanRepository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public FanDTO findById(Long id) {
        return toDto(getFan(id));
    }

    @Transactional
    public FanDTO create(FanDTO dto) {
        ensureEmailAvailable(dto.getEmail().trim(), null);
        Fan fan = new Fan();
        updateFields(fan, dto);
        return toDto(fanRepository.save(fan));
    }

    @Transactional
    public FanDTO update(Long id, FanDTO dto) {
        Fan fan = getFan(id);
        ensureEmailAvailable(dto.getEmail().trim(), id);
        updateFields(fan, dto);
        return toDto(fanRepository.save(fan));
    }

    @Transactional
    public void delete(Long id) {
        fanRepository.delete(getFan(id));
    }

    private Fan getFan(Long id) {
        return fanRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fã não encontrado"));
    }

    private void ensureEmailAvailable(String email, Long currentId) {
        fanRepository.findByEmailIgnoreCase(email).ifPresent(existing -> {
            if (!existing.getId().equals(currentId)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
            }
        });
    }

    private void updateFields(Fan fan, FanDTO dto) {
        fan.setName(dto.getName().trim());
        fan.setEmail(dto.getEmail().trim());
        fan.setBirthDate(dto.getBirthDate());
        fan.setGender(dto.getGender().trim());
        Team favoriteTeam = teamRepository.findById(dto.getFavoriteTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time favorito inválido"));
        fan.setFavoriteTeam(favoriteTeam);
        fan.setCity(dto.getCity().trim());
        fan.setState(dto.getState().trim());
        fan.setCountry(dto.getCountry().trim());
        fan.setStadiumFrequency(dto.getStadiumFrequency());
    }

    private FanDTO toDto(Fan fan) {
        FanDTO dto = new FanDTO();
        dto.setId(fan.getId());
        dto.setName(fan.getName());
        dto.setEmail(fan.getEmail());
        dto.setBirthDate(fan.getBirthDate());
        dto.setGender(fan.getGender());
        dto.setFavoriteTeamId(fan.getFavoriteTeam().getId());
        dto.setCity(fan.getCity());
        dto.setState(fan.getState());
        dto.setCountry(fan.getCountry());
        dto.setStadiumFrequency(fan.getStadiumFrequency());
        return dto;
    }
}

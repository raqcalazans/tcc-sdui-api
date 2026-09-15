package com.selecaoglobocom.brasileirao_api.service;

import com.selecaoglobocom.brasileirao_api.dto.GameDTO;
import com.selecaoglobocom.brasileirao_api.dto.GameGroupDTO;
import com.selecaoglobocom.brasileirao_api.dto.GameScreenDTO;
import com.selecaoglobocom.brasileirao_api.entity.FilterGroup;
import com.selecaoglobocom.brasileirao_api.mapper.GameMapper;
import com.selecaoglobocom.brasileirao_api.repository.FilterGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final FilterGroupRepository filterGroupRepository;
    private final GameMapper gameMapper;

    public GameService(FilterGroupRepository filterGroupRepository, GameMapper gameMapper) {
        this.filterGroupRepository = filterGroupRepository;
        this.gameMapper = gameMapper;
    }

    public GameScreenDTO findAllGamesGrouped() {
        List<FilterGroup> filterGroups = filterGroupRepository.findAllWithGamesOrderByDisplayOrderAsc();

        List<GameGroupDTO> groups = filterGroups.stream()
                .map(group -> {
                    List<GameDTO> gameDTOs = gameMapper.toDto(group.getGames());
                    return new GameGroupDTO(group.getTitle(), gameDTOs);
                })
                .collect(Collectors.toList());

        return new GameScreenDTO("JOGOS", groups);
    }
}
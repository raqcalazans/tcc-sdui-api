package com.sdui.brasileirao_api.service;

import com.sdui.brasileirao_api.dto.GameDTO;
import com.sdui.brasileirao_api.dto.GameGroupDTO;
import com.sdui.brasileirao_api.dto.GameScreenDTO;
import com.sdui.brasileirao_api.mapper.GameMapper;
import com.sdui.brasileirao_api.model.FilterGroup;
import com.sdui.brasileirao_api.model.Game;
import com.sdui.brasileirao_api.repository.FilterGroupRepository;
import com.sdui.brasileirao_api.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final FilterGroupRepository filterGroupRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameService(FilterGroupRepository filterGroupRepository, 
                       GameRepository gameRepository, 
                       GameMapper gameMapper) {
        this.filterGroupRepository = filterGroupRepository;
        this.gameRepository = gameRepository;
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

    public GameDTO findById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado com o ID: " + id));
                
        return gameMapper.toDto(game);
    }
}
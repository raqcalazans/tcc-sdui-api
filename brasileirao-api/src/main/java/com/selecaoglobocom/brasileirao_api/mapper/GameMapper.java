package com.selecaoglobocom.brasileirao_api.mapper;

import com.selecaoglobocom.brasileirao_api.dto.GameDTO;
import com.selecaoglobocom.brasileirao_api.dto.GameEventDTO;
import com.selecaoglobocom.brasileirao_api.entity.Game;
import com.selecaoglobocom.brasileirao_api.entity.GameEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    private final TeamMapper teamMapper;

    public GameMapper(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    public GameDTO toDto(Game game) {
        if (game == null) {
            return null;
        }

        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setHomeGoals(game.getHomeGoals());
        dto.setAwayGoals(game.getAwayGoals());
        dto.setGameDateTime(game.getGameDateTime());
        dto.setStadium(game.getStadium());
        dto.setLive(game.isLive());

        dto.setHomeTeam(teamMapper.toDto(game.getHomeTeam()));
        dto.setAwayTeam(teamMapper.toDto(game.getAwayTeam()));

        if (game.getEvents() != null) {
            List<GameEventDTO> eventDTOs = game.getEvents().stream()
                    .map(eventEntity -> toDto(eventEntity, game))
                    .collect(Collectors.toList());
            dto.setEvents(eventDTOs);
        } else {
            dto.setEvents(Collections.emptyList());
        }

        return dto;
    }

    public List<GameDTO> toDto(List<Game> games) {
        return games.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private GameEventDTO toDto(GameEvent event, Game parentGame) {
        if (event == null) {
            return null;
        }
        GameEventDTO dto = new GameEventDTO();
        dto.setId(event.getId());
        dto.setDescription(event.getDescription());

        Duration duration = Duration.between(parentGame.getGameDateTime(), event.getEventTime());
        long minutes = duration.toMinutes();
        dto.setTimeInGame(String.format("%d'", minutes));

        return dto;
    }
}
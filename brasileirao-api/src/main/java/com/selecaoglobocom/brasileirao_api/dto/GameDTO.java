package com.selecaoglobocom.brasileirao_api.dto;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class GameDTO {
    private Integer id;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
    private Integer homeGoals;
    private Integer awayGoals;
    private OffsetDateTime gameDateTime;
    private String stadium;
    private List<GameEventDTO> events;

    @JsonProperty("is_live")
    private boolean isLive;
}
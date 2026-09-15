package com.selecaoglobocom.brasileirao_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class GameScreenDTO {
    @JsonProperty("screen_title")
    private String screenTitle;
    private List<GameGroupDTO> groups;
}
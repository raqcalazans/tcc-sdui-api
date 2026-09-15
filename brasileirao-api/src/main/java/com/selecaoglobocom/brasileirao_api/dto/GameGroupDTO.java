package com.selecaoglobocom.brasileirao_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class GameGroupDTO {
    private String title;
    private List<GameDTO> games;
}
package com.selecaoglobocom.brasileirao_api.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private int id;
    private String name;
    private String acronym;
    private URL logoURL;
    private String description;
}

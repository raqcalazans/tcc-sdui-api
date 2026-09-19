package com.pesquisa.brasileirao_api.dto.sdui;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ButtonDTO {
    private String text;
    private String url;
}
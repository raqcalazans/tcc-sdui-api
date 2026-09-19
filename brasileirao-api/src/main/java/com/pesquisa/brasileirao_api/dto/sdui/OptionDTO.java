package com.pesquisa.brasileirao_api.dto.sdui;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDTO {
    private String id;    // Valor real (ex: "1", "2" ou "BRONZE", "OURO")
    private String label; // Valor visual (ex: "Flamengo", "Plano Ouro")
}

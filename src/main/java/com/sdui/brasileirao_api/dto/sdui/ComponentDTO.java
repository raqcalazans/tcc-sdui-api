package com.sdui.brasileirao_api.dto.sdui;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ComponentDTO {
    private String id;          // A chave do payload (ex: "favoriteTeamId")
    private String type;        // O componente nativo (ex: "TEXT_FIELD", "DROP_DOWN", "RADIO_GROUP", "TOGGLE")
    private String label;       // Texto de exibição (ex: "Selecione seu time")
    private boolean required;   // Regra de validação delegada ao cliente
    private List<OptionDTO> options; // Usado para popular Pickers e Radios
}
package com.pesquisa.brasileirao_api.dto.sdui;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class FormScreenDTO {
    private String screenTitle;
    private List<ComponentDTO> components;
    private ButtonDTO submitButton;
}
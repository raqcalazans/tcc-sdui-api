package com.sdui.amantesdofutebol.dto.sdui;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenActionDTO {
    private String id;
    private String title;
    private String role;
    private Boolean requiresConfirmation;
}

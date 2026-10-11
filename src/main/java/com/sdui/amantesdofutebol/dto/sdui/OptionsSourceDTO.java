package com.sdui.amantesdofutebol.dto.sdui;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionsSourceDTO {
    private String method;
    private String path;
    private String valueField;
    private String labelField;
}

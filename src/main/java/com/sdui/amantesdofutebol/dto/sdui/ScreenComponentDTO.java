package com.sdui.amantesdofutebol.dto.sdui;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenComponentDTO {
    private String id;
    private String type;
    private String field;
    private List<String> fields;
    private String label;
    private Boolean required;
    private String keyboard;
    private String placeholder;
    private String format;
    private List<OptionDTO> options;
    private OptionsSourceDTO optionsSource;
    private String titleField;
    private String subtitleField;
    private OptionsSourceDTO subtitleOptionsSource;
    private String selectAction;
}

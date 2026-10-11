package com.sdui.amantesdofutebol.dto.sdui;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScreenDefinitionDTO {
    private String screenId;
    private String screenTitle;
    private String screenType;
    private List<ScreenSectionDTO> sections;
    private List<ScreenActionDTO> actions;
}

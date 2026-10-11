package com.sdui.amantesdofutebol.dto.sdui;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScreenSectionDTO {
    private String id;
    private String title;
    private List<ScreenComponentDTO> components;
}

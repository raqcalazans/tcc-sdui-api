package com.sdui.amantesdofutebol.dto.sdui;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ComponentDTO {
    private String id;
    private String type;
    private String label;
    private boolean required;
    private List<OptionDTO> options;
}

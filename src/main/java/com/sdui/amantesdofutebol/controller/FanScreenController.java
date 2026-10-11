package com.sdui.amantesdofutebol.controller;

import com.sdui.amantesdofutebol.dto.sdui.ScreenDefinitionDTO;
import com.sdui.amantesdofutebol.factory.FanScreenFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/ui/fans", "/api/v1/ui/fans"})
public class FanScreenController {

    private final FanScreenFactory screenFactory;

    public FanScreenController(FanScreenFactory screenFactory) {
        this.screenFactory = screenFactory;
    }

    @GetMapping("/list-screen")
    public ScreenDefinitionDTO getListScreen() {
        return screenFactory.buildListScreen();
    }

    @GetMapping("/registration-screen")
    public ScreenDefinitionDTO getRegistrationScreen() {
        return screenFactory.buildRegistrationScreen();
    }

    @GetMapping("/{id}/detail-screen")
    public ScreenDefinitionDTO getDetailScreen(@PathVariable Long id) {
        return screenFactory.buildDetailScreen();
    }

    @GetMapping("/{id}/edit-screen")
    public ScreenDefinitionDTO getEditScreen(@PathVariable Long id) {
        return screenFactory.buildEditScreen();
    }
}

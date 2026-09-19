package com.sdui.brasileirao_api.controller;

import com.sdui.brasileirao_api.dto.FanDTO;
import com.sdui.brasileirao_api.dto.sdui.FormScreenDTO;
import com.sdui.brasileirao_api.factory.FanRegisterScreenFactory;
import com.sdui.brasileirao_api.model.Fan;
import com.sdui.brasileirao_api.service.FanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fans")
public class FanController {

    private final FanRegisterScreenFactory screenFactory;
    private final FanService fanService;

    public FanController(FanRegisterScreenFactory screenFactory, FanService fanService) {
        this.screenFactory = screenFactory;
        this.fanService = fanService;
    }

    @GetMapping("/registration-screen")
    public ResponseEntity<FormScreenDTO> getRegistrationScreen() {
        FormScreenDTO screen = screenFactory.buildRegistrationScreen();
        return ResponseEntity.ok(screen);
    }
    
    @PostMapping
    public ResponseEntity<Fan> createFan(@RequestBody FanDTO fanDTO) {
        Fan savedFan = fanService.saveFan(fanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFan);
    }
}
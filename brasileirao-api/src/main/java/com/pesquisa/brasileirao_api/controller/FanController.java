package com.pesquisa.brasileirao_api.controller;

import com.pesquisa.brasileirao_api.dto.FanDTO;
import com.pesquisa.brasileirao_api.dto.sdui.FormScreenDTO;
import com.pesquisa.brasileirao_api.factory.FanRegisterScreenFactory;
import com.pesquisa.brasileirao_api.model.Fan;
import com.pesquisa.brasileirao_api.service.FanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fans") // Alterado para /fans para centralizar a criação e gerir as rotas adequadamente
public class FanController {

    private final FanRegisterScreenFactory screenFactory;
    private final FanService fanService; // Declarado aqui

    // Injeção de dependências via construtor unificado
    public FanController(FanRegisterScreenFactory screenFactory, FanService fanService) {
        this.screenFactory = screenFactory;
        this.fanService = fanService;
    }

    // O app iOS bate neste endpoint para pedir a tela (mantém o prefixo /ui se preferir, ou direto aqui)
    @GetMapping("/registration-screen")
    public ResponseEntity<FormScreenDTO> getRegistrationScreen() {
        FormScreenDTO screen = screenFactory.buildRegistrationScreen();
        return ResponseEntity.ok(screen);
    }

    // Endpoint POST para receber os dados do formulário submetidos pelo app iOS
    @PostMapping
    public ResponseEntity<Fan> createFan(@RequestBody FanDTO fanDTO) {
        Fan savedFan = fanService.saveFan(fanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFan);
    }
}
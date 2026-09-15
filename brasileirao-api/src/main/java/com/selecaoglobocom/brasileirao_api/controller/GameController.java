package com.selecaoglobocom.brasileirao_api.controller;

import com.selecaoglobocom.brasileirao_api.dto.GameScreenDTO;
import com.selecaoglobocom.brasileirao_api.service.GameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public GameScreenDTO getAllGames() { 
        return gameService.findAllGamesGrouped();
    }
}

package com.sdui.brasileirao_api.controller;

import com.sdui.brasileirao_api.dto.GameDTO;
import com.sdui.brasileirao_api.dto.GameScreenDTO;
import com.sdui.brasileirao_api.service.GameService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public GameScreenDTO getAllGames() { 
        return gameService.findAllGamesGrouped();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        GameDTO gameDTO = gameService.findById(id);
        return ResponseEntity.ok(gameDTO);
    }
}

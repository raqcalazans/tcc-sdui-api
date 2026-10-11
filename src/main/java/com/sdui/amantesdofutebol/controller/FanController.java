package com.sdui.amantesdofutebol.controller;

import com.sdui.amantesdofutebol.dto.FanDTO;
import com.sdui.amantesdofutebol.dto.sdui.ScreenDefinitionDTO;
import com.sdui.amantesdofutebol.factory.FanScreenFactory;
import com.sdui.amantesdofutebol.service.FanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/fans", "/api/v1/fans"})
public class FanController {

    private final FanService fanService;
    private final FanScreenFactory screenFactory;

    public FanController(FanService fanService, FanScreenFactory screenFactory) {
        this.fanService = fanService;
        this.screenFactory = screenFactory;
    }

    @GetMapping("/registration-screen")
    public ResponseEntity<ScreenDefinitionDTO> getRegistrationScreen() {
        return ResponseEntity.ok(screenFactory.buildRegistrationScreen());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FanDTO> create(@Valid @RequestBody FanDTO fanDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fanService.create(fanDTO));
    }

    @GetMapping
    public List<FanDTO> findAll() {
        return fanService.findAll();
    }

    @GetMapping("/{id}")
    public FanDTO findById(@PathVariable Long id) {
        return fanService.findById(id);
    }

    @PutMapping("/{id}")
    public FanDTO update(@PathVariable Long id, @Valid @RequestBody FanDTO fanDTO) {
        return fanService.update(id, fanDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        fanService.delete(id);
    }
}

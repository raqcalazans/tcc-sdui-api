package com.sdui.amantesdofutebol.controller;

import com.sdui.amantesdofutebol.dto.FanDTO;
import com.sdui.amantesdofutebol.dto.sdui.ScreenDefinitionDTO;
import com.sdui.amantesdofutebol.factory.FanScreenFactory;
import com.sdui.amantesdofutebol.model.StadiumFrequency;
import com.sdui.amantesdofutebol.service.FanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FanControllerTest {

    @Mock private FanService fanService;
    @Mock private FanScreenFactory screenFactory;
    private FanController controller;

    @BeforeEach
    void setUp() {
        controller = new FanController(fanService, screenFactory);
    }

    @Test
    void getRegistrationScreenDelegatesToFactory() {
        ScreenDefinitionDTO screen = ScreenDefinitionDTO.builder().screenId("fan-registration")
                .screenTitle("Cadastro de fã").screenType("FORM").build();
        when(screenFactory.buildRegistrationScreen()).thenReturn(screen);

        var response = controller.getRegistrationScreen();

        assertEquals(200, response.getStatusCode().value());
        assertSame(screen, response.getBody());
        verify(screenFactory).buildRegistrationScreen();
    }

    @Test
    void createDelegatesToService() {
        FanDTO input = dto();
        when(fanService.create(input)).thenReturn(input);

        var response = controller.create(input);

        assertEquals(CREATED, response.getStatusCode());
        assertSame(input, response.getBody());
        verify(fanService).create(input);
    }

    @Test
    void findAllDelegatesToService() {
        List<FanDTO> fans = List.of(dto());
        when(fanService.findAll()).thenReturn(fans);

        assertSame(fans, controller.findAll());
        verify(fanService).findAll();
    }

    @Test
    void findByIdDelegatesToService() {
        FanDTO fan = dto();
        when(fanService.findById(7L)).thenReturn(fan);

        assertSame(fan, controller.findById(7L));
        verify(fanService).findById(7L);
    }

    @Test
    void updateDelegatesToService() {
        FanDTO input = dto();
        when(fanService.update(7L, input)).thenReturn(input);

        assertSame(input, controller.update(7L, input));
        verify(fanService).update(7L, input);
    }

    @Test
    void deleteDelegatesToService() {
        controller.delete(7L);

        verify(fanService).delete(7L);
    }

    private FanDTO dto() {
        FanDTO dto = new FanDTO();
        dto.setId(7L);
        dto.setName("Ana Silva");
        dto.setEmail("ana@example.com");
        dto.setBirthDate(LocalDate.of(1995, 4, 20));
        dto.setGender("Feminino");
        dto.setFavoriteTeamId("FLA");
        dto.setCity("Rio de Janeiro");
        dto.setState("RJ");
        dto.setCountry("Brasil");
        dto.setStadiumFrequency(StadiumFrequency.AS_VEZES);
        return dto;
    }
}

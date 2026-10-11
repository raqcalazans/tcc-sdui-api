package com.sdui.amantesdofutebol.controller;

import com.sdui.amantesdofutebol.dto.sdui.ScreenDefinitionDTO;
import com.sdui.amantesdofutebol.factory.FanScreenFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FanScreenControllerTest {

    @Mock
    private FanScreenFactory screenFactory;

    private FanScreenController controller;

    @BeforeEach
    void setUp() {
        controller = new FanScreenController(screenFactory);
    }

    @Test
    void getListScreenReturnsListConfiguration() {
        ScreenDefinitionDTO screen = screen("fan-list");
        when(screenFactory.buildListScreen()).thenReturn(screen);

        assertSame(screen, controller.getListScreen());
        verify(screenFactory).buildListScreen();
    }

    @Test
    void getRegistrationScreenReturnsRegistrationConfiguration() {
        ScreenDefinitionDTO screen = screen("fan-registration");
        when(screenFactory.buildRegistrationScreen()).thenReturn(screen);

        assertSame(screen, controller.getRegistrationScreen());
        verify(screenFactory).buildRegistrationScreen();
    }

    @Test
    void getDetailScreenReturnsDetailConfiguration() {
        ScreenDefinitionDTO screen = screen("fan-detail");
        when(screenFactory.buildDetailScreen()).thenReturn(screen);

        assertSame(screen, controller.getDetailScreen(12L));
        verify(screenFactory).buildDetailScreen();
    }

    @Test
    void getEditScreenReturnsEditConfiguration() {
        ScreenDefinitionDTO screen = screen("fan-edit");
        when(screenFactory.buildEditScreen()).thenReturn(screen);

        assertSame(screen, controller.getEditScreen(12L));
        verify(screenFactory).buildEditScreen();
    }

    private ScreenDefinitionDTO screen(String screenId) {
        return ScreenDefinitionDTO.builder().screenId(screenId).build();
    }
}

package com.sdui.amantesdofutebol;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

class AmantesDoFutebolApplicationTests {

    @Test
    void mainDelegatesToSpringApplication() {
        assertNotNull(new AmantesDoFutebolApplication());
        String[] args = {"--server.port=0"};
        try (var springApplication = mockStatic(SpringApplication.class)) {
            AmantesDoFutebolApplication.main(args);
            springApplication.verify(() -> SpringApplication.run(AmantesDoFutebolApplication.class, args));
        }
    }
}

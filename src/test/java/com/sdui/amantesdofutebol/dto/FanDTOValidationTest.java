package com.sdui.amantesdofutebol.dto;

import com.sdui.amantesdofutebol.model.StadiumFrequency;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FanDTOValidationTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void createValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        factory.close();
    }

    @Test
    void acceptsValidFanData() {
        assertTrue(validator.validate(validDto()).isEmpty());
    }

    @Test
    void requiresAllFanFields() {
        Set<String> invalidFields = validator.validate(new FanDTO()).stream()
                .map(error -> error.getPropertyPath().toString())
                .collect(Collectors.toSet());

        assertEquals(Set.of("name", "email", "birthDate", "gender", "favoriteTeamId",
                "city", "state", "country", "stadiumFrequency"), invalidFields);
    }

    @Test
    void rejectsInvalidEmailAndNonPastBirthDate() {
        FanDTO dto = validDto();
        dto.setEmail("not-an-email");
        dto.setBirthDate(LocalDate.now().plusDays(1));

        Set<String> invalidFields = validator.validate(dto).stream()
                .map(error -> error.getPropertyPath().toString())
                .collect(Collectors.toSet());

        assertTrue(invalidFields.contains("email"));
        assertTrue(invalidFields.contains("birthDate"));
    }

    private static FanDTO validDto() {
        FanDTO dto = new FanDTO();
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

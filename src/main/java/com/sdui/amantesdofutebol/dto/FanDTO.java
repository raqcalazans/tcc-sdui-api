package com.sdui.amantesdofutebol.dto;

import com.sdui.amantesdofutebol.model.StadiumFrequency;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FanDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    @NotBlank
    private String favoriteTeamId;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotNull
    private StadiumFrequency stadiumFrequency;
}

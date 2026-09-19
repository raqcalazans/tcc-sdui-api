package com.pesquisa.brasileirao_api.dto;

import lombok.Data;

@Data
public class FanDTO {
    private String name;
    private String email;
    private Integer favoriteTeamId;
    private String subscriptionPlan;
    private boolean receivesAlerts;
}
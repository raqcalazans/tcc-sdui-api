package com.sdui.amantesdofutebol.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StadiumFrequencyTest {

    @Test
    void exposesAllAllowedFrequencyValues() {
        assertEquals(4, StadiumFrequency.values().length);
        assertEquals(StadiumFrequency.SEMPRE, StadiumFrequency.valueOf("SEMPRE"));
        assertEquals(StadiumFrequency.AS_VEZES, StadiumFrequency.valueOf("AS_VEZES"));
        assertEquals(StadiumFrequency.RARAMENTE, StadiumFrequency.valueOf("RARAMENTE"));
        assertEquals(StadiumFrequency.NUNCA, StadiumFrequency.valueOf("NUNCA"));
    }
}

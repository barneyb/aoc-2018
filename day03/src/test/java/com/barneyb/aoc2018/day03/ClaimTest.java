package com.barneyb.aoc2018.day03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClaimTest {

    public static final String LINE = "#9 @ 166,500: 12x15";

    @Test
    public void parseLine() {
        assertEquals(new Claim(
                9, 166, 500, 12, 15
        ), Claim.fromString(LINE));
    }

    @Test
    public void toString_() {
        assertEquals(LINE, Claim.fromString(LINE).toString());
    }

}
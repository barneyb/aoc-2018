package com.barneyb.aoc2018.day04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuardMinuteTest {

    @Test
    public void product() {
        assertEquals(4455, new GuardMinute(99, 45).product());
    }

}
package com.barneyb.aoc2018.day04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimestampTest {

    @Test
    public void strings() {
        assertEquals(new Timestamp(
                1980, 6, 10, 0, 8
        ), Timestamp.fromString("1980-06-10 00:08"));
    }

}
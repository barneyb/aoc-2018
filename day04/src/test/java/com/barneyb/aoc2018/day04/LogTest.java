package com.barneyb.aoc2018.day04;

import org.junit.Test;

import static com.barneyb.aoc2018.day04.Day04Test.*;
import static org.junit.Assert.assertArrayEquals;

public class LogTest {

    @Test
    public void parse() {
        assertArrayEquals(RECORDS_10, Log.parse(GUARD_10).records());
        assertArrayEquals(RECORDS_99, Log.parse(GUARD_99).records());
    }

}
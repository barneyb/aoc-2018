package com.barneyb.aoc2018.day02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    private static String[] EXAMPLE_1 = {
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab",
    };

    @Test
    public void partOne() {
        assertEquals(12, new Day02().getPartOne(EXAMPLE_1));
    }



}

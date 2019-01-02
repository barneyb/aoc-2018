package com.barneyb.aoc2018.day02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    static String[] EXAMPLE_1 = {
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab",
    };

    static String[] EXAMPLE_2 = {
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz",
    };

    @Test
    public void partOne() {
        assertEquals(12, new Warehouse(EXAMPLE_1).checksum());
    }

    @Test
    public void partTwo() {
        assertEquals("fgij", new Warehouse(EXAMPLE_2).fabricBoxesCommonLetters());
    }

}

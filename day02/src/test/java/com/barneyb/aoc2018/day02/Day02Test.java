package com.barneyb.aoc2018.day02;

import org.junit.Test;

import static org.junit.Assert.*;

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
        assertEquals("fgij", new Day02().getPartTwo(EXAMPLE_2));
    }

    @Test
    public void areNeighbors() {
        Day02 d = new Day02();
        assertFalse(d.areNeighbors("abc", "abc")); // identical
        assertTrue(d.areNeighbors("abc", "abx")); // one diff
        assertFalse(d.areNeighbors("abc", "axy")); // two diffs
    }

    @Test
    public void commonLetters() {
        Day02 d = new Day02();
        assertEquals("fgij", d.commonLetters("fghij", "fguij"));
    }

}

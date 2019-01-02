package com.barneyb.aoc2018.day02;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void areNeighbors() {
        assertFalse(Utils.areNeighbors("abc", "abc")); // identical
        assertTrue(Utils.areNeighbors("abc", "abx")); // one diff
        assertFalse(Utils.areNeighbors("abc", "axy")); // two diffs
    }

    @Test
    public void commonLetters() {
        assertEquals("fgij", Utils.commonLetters("fghij", "fguij"));
    }

}
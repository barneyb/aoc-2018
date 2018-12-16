package com.barneyb.aoc2018.day16;

import org.junit.Test;

import static com.barneyb.aoc2018.day16.Day16Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertArrayEquals;

public class SampleTest {

    @Test
    public void parse() {
        String[] lines = EXAMPLE_INPUT.trim().split("\n");
        Sample s = Sample.parse(lines[0], lines[1], lines[2]);
        assertArrayEquals(new int[] {3, 2, 1, 1}, s.before());
        assertArrayEquals(new int[] {9, 2, 1, 2}, s.instruction());
        assertArrayEquals(new int[] {3, 2, 2, 1}, s.after());
    }

}
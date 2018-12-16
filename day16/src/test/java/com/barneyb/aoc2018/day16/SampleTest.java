package com.barneyb.aoc2018.day16;

import org.junit.Test;

import static com.barneyb.aoc2018.day16.Day16Test.EXAMPLE_INPUT;
import static org.junit.Assert.*;

public class SampleTest {

    static final Sample EXAMPLE = new Sample(
            new int[] {3, 2, 1, 1},
            new Instruction(9, 2, 1, 2),
            new int[] {3, 2, 2, 1}
    );

    @Test
    public void parse() {
        String[] lines = EXAMPLE_INPUT.trim().split("\n");
        Sample s = Sample.parse(lines[0], lines[1], lines[2]);
        assertArrayEquals(EXAMPLE.before(), s.before());
        assertEquals(EXAMPLE.instruction(), s.instruction());
        assertArrayEquals(EXAMPLE.after(), s.after());
    }

    @Test
    public void test() {
        assertFalse(EXAMPLE.test((registers, a, b, c) -> {
            // no op
        }));
        assertTrue(EXAMPLE.test((registers, a, b, c) -> registers[2] = 2));
    }
}
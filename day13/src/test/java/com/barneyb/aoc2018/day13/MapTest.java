package com.barneyb.aoc2018.day13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void strings() {
        assertEquals(
                // this looks janky cuzza escapes
                "/-A-\\\n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | B  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/\n", Map.parse(Day13Test.EXAMPLE_INPUT).toString());
    }
}
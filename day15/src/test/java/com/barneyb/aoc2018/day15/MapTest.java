package com.barneyb.aoc2018.day15;

import org.junit.Test;

import static com.barneyb.aoc2018.day15.Day15Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void strings() {
        Map m = Map.parse(EXAMPLE_INPUT);
        assertEquals(EXAMPLE_INPUT, m.toString());
        assertEquals("#######\n" +
                "#.a...#\n" +
                "#...Ab#\n" +
                "#.#.#c#\n" +
                "#..d#B#\n" +
                "#.....#\n" +
                "#######",
                m.toString(true));
    }

}
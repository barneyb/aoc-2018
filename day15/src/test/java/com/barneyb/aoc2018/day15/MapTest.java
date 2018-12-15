package com.barneyb.aoc2018.day15;

import org.junit.Test;

import static com.barneyb.aoc2018.day15.Day15Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void strings() {
        Map m = Map.parse(EXAMPLE_INPUT);
        assertEquals(
                "#######\n" +
                "#.a...# a(300)\n" +
                "#...Ab# A(300) b(300)\n" +
                "#.#.#c# c(300)\n" +
                "#..d#B# d(300) B(300)\n" +
                "#.....#\n" +
                "#######",
                m.toString());
    }

}
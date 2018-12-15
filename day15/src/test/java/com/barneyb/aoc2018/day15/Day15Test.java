package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Test {

    static final String EXAMPLE_INPUT =
            "#######\n" +
            "#.G...#\n" +
            "#...EG#\n" +
            "#.#.#G#\n" +
            "#..G#E#\n" +
            "#.....#\n" +
            "#######";

    @Test
    public void solve() {
        Day15 d = new Day15();
        Answers a = d.solve(EXAMPLE_INPUT);
        // 47 rounds, goblins win w/ 200+131+59+200 = 590 HP
        assertEquals("27730", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

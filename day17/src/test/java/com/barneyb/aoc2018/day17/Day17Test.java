package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day17Test {

    static final String EXAMPLE_INPUT =
            "x=495, y=2..7\n" +
            "y=7, x=495..501\n" +
            "x=501, y=3..7\n" +
            "x=498, y=2..4\n" +
            "x=506, y=1..2\n" +
            "x=498, y=10..13\n" +
            "x=504, y=10..13\n" +
            "y=13, x=498..504";

    @Test
    public void solve() {
        Answers a = new Day17().solve(EXAMPLE_INPUT);
        assertEquals("57", a.getPartOne());
        assertEquals("29", a.getPartTwo());
    }

}

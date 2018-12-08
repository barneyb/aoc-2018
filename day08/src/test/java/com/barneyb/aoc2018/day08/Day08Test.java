package com.barneyb.aoc2018.day08;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08Test {

    private static final String EXAMPLE_INPUT = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

    @Test
    public void solve() {
        Answers a = new Day08().solve(EXAMPLE_INPUT);
        assertEquals("138", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

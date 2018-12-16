package com.barneyb.aoc2018.day16;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16Test {

    static final String EXAMPLE_INPUT =
            "Before: [3, 2, 1, 1]\n" +
            "9 2 1 2\n" +
            "After:  [3, 2, 2, 1]\n";

    @Test
    public void solve() {
        Answers a = new Day16().solvePartOne(EXAMPLE_INPUT);
        assertEquals("1", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

package com.barneyb.aoc2018.day03;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day03Test {

    private static final String EXAMPLE_INPUT = "#1 @ 1,3: 4x4\n" +
            "#2 @ 3,1: 4x4\n" +
            "#3 @ 5,5: 2x2";
    private static final Claim[] EXAMPLE_CLAIMS = {
            new Claim(1, 1, 3, 4, 4),
            new Claim(2, 3, 1, 4, 4),
            new Claim(3, 5, 5, 2, 2)
    };

    @Test
    public void solve() {
        Answers a = new Day03().solve(EXAMPLE_INPUT);
        assertEquals("4", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void parse() {
        assertArrayEquals(EXAMPLE_CLAIMS, Day03.parse(EXAMPLE_INPUT));
    }
}

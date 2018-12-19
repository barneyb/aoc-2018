package com.barneyb.aoc2018.day19;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day19Test {

    static final String EXAMPLE_INPUT =
            "#ip 0\n" +
            "seti 5 0 1\n" +
            "seti 6 0 2\n" +
            "addi 0 1 0\n" +
            "addr 1 2 3\n" +
            "setr 1 0 0\n" +
            "seti 8 0 4\n" +
            "seti 9 0 5";

    @Test
    public void solve() {
        Answers a = new Day19().solve(EXAMPLE_INPUT);
        assertEquals("6", a.getPartOne());
        assertEquals("6", a.getPartTwo());
    }

    @Test
    public void sumOfFactors() {
        assertEquals(1 + 2 + 3 + 6, Day19.sumOfFactorPairs(6));
        assertEquals(1 + 5 + 5 + 25, Day19.sumOfFactorPairs(25));
    }
}

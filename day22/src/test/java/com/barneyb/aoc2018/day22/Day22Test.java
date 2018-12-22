package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day22Test {

    static final String EXAMPLE_INPUT = "depth: 510\n" +
            "target: 10,10";

    @Test
    public void solve() {
        Answers a = new Day22().solve(EXAMPLE_INPUT);
        assertEquals("114", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

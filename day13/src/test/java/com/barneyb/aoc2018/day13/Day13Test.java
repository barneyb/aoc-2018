package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.api.Day;
import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day13Test {

    private static final String EXAMPLE_INPUT = "  cat  ";

    @Test
    public void solve() {
        Answers a = new Day13().solve(EXAMPLE_INPUT);
        assertEquals("7", a.getPartOne());
        assertEquals("3", a.getPartTwo());
    }

}

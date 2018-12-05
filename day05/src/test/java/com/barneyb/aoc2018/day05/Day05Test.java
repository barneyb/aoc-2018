package com.barneyb.aoc2018.day05;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {

    @Test
    public void solve() {
        Answers a = new Day05().solve("dabAcCaCBAcCcaDA");
        assertEquals("10", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23Test {

    static final String EXAMPLE_INPUT =
            "pos=<0,0,0>, r=4\n" +
            "pos=<1,0,0>, r=1\n" +
            "pos=<4,0,0>, r=3\n" +
            "pos=<0,2,0>, r=1\n" +
            "pos=<0,5,0>, r=3\n" +
            "pos=<0,0,3>, r=1\n" +
            "pos=<1,1,1>, r=1\n" +
            "pos=<1,1,2>, r=1\n" +
            "pos=<1,3,1>, r=1";

    @Test
    public void solve() {
        Answers a = new Day23().solve(EXAMPLE_INPUT);
        assertEquals("7", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23Test {

    static final String EXAMPLE_ONE_INPUT =
            "pos=<0,0,0>, r=4\n" +
            "pos=<1,0,0>, r=1\n" +
            "pos=<4,0,0>, r=3\n" +
            "pos=<0,2,0>, r=1\n" +
            "pos=<0,5,0>, r=3\n" +
            "pos=<0,0,3>, r=1\n" +
            "pos=<1,1,1>, r=1\n" +
            "pos=<1,1,2>, r=1\n" +
            "pos=<1,3,1>, r=1";

    static final String EXAMPLE_TWO_INPUT =
            "pos=<10,12,12>, r=2\n" +
            "pos=<12,14,12>, r=2\n" +
            "pos=<16,12,12>, r=4\n" +
            "pos=<14,14,14>, r=6\n" +
            "pos=<50,50,50>, r=200\n" +
            "pos=<10,10,10>, r=5";

    @Test
    public void solveOne() {
        Answers a = new Day23().solve(EXAMPLE_ONE_INPUT);
        assertEquals("7", a.getPartOne());
//        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void solveTwo() {
        Answers a = new Day23().solve(EXAMPLE_TWO_INPUT);
        assertEquals("6", a.getPartOne());
        assertEquals("36", a.getPartTwo());
    }

    @Test
    public void partTwo() {
        Swarm swarm = Swarm.parse(EXAMPLE_TWO_INPUT);
        assertEquals(36, swarm.distanceToIdealPoint());
    }
}

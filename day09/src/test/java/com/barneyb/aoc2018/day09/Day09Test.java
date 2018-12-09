package com.barneyb.aoc2018.day09;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day09Test {

    private static final String EXAMPLE_INPUT = "9 players; last marble is worth 25 points";

    @Test
    public void solve() {
        Answers a = new Day09().solve(EXAMPLE_INPUT);
        assertEquals("32", a.getPartOne());
        assertEquals("22563", a.getPartTwo());
    }

    @Test
    public void exampleTwo() {
        assertEquals(8317, Day09.partOne(10, 1618));
    }

    @Test
    public void exampleThree() {
        assertEquals(146373, Day09.partOne(13, 7999));
    }

    @Test
    public void exampleFour() {
        assertEquals(2764, Day09.partOne(17, 1104));
    }

    @Test
    public void exampleFive() {
        assertEquals(54718, Day09.partOne(21, 6111));
    }

    @Test
    public void exampleSix() {
        assertEquals(37305, Day09.partOne(30, 5807));
    }

}

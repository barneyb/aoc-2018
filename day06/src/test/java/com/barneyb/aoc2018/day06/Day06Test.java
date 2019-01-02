package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Point;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day06Test {

    static final String EXAMPLE_INPUT = "1, 1\n" +
            "1, 6\n" +
            "8, 3\n" +
            "3, 4\n" +
            "5, 5\n" +
            "8, 9";

    static final Point[] EXAMPLE_POINTS = {
            new Point(1, 1),
            new Point(1, 6),
            new Point(8, 3),
            new Point(3, 4),
            new Point(5, 5),
            new Point(8, 9)
    };

    @Test
    public void solve() {
        Answers a = new Day06().solve(EXAMPLE_INPUT);
        assertEquals("17", a.getPartOne());
    }

    @Test
    public void parse() {
        assertArrayEquals(EXAMPLE_POINTS, Plotter.parse(EXAMPLE_INPUT).points());
    }

    @Test
    public void partTwo() {
        assertEquals(16, new Plotter(EXAMPLE_POINTS).regionWithin(32));
    }
}

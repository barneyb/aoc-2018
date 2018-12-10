package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Resources;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day10Test {

    private static final String EXAMPLE_INPUT = Resources.asText("example.txt");

    private static final Particle[] EXAMPLE_PARTICLES = {
        new Particle(new Point( 9,  1), new Point( 0,  2)),
        new Particle(new Point( 7,  0), new Point(-1,  0)),
        new Particle(new Point( 3, -2), new Point(-1,  1)),
        new Particle(new Point( 6, 10), new Point(-2, -1)),
        new Particle(new Point( 2, -4), new Point( 2,  2)),
        new Particle(new Point(-6, 10), new Point( 2, -2)),
        new Particle(new Point( 1,  8), new Point( 1, -1)),
        new Particle(new Point( 1,  7), new Point( 1,  0)),
        new Particle(new Point(-3, 11), new Point( 1, -2)),
        new Particle(new Point( 7,  6), new Point(-1, -1)),
        new Particle(new Point(-2,  3), new Point( 1,  0)),
        new Particle(new Point(-4,  3), new Point( 2,  0)),
        new Particle(new Point(10, -3), new Point(-1,  1)),
        new Particle(new Point( 5, 11), new Point( 1, -2)),
        new Particle(new Point( 4,  7), new Point( 0, -1)),
        new Particle(new Point( 8, -2), new Point( 0,  1)),
        new Particle(new Point(15,  0), new Point(-2,  0)),
        new Particle(new Point( 1,  6), new Point( 1,  0)),
        new Particle(new Point( 8,  9), new Point( 0, -1)),
        new Particle(new Point( 3,  3), new Point(-1,  1)),
        new Particle(new Point( 0,  5), new Point( 0, -1)),
        new Particle(new Point(-2,  2), new Point( 2,  0)),
        new Particle(new Point( 5, -2), new Point( 1,  2)),
        new Particle(new Point( 1,  4), new Point( 2,  1)),
        new Particle(new Point(-2,  7), new Point( 2, -2)),
        new Particle(new Point( 3,  6), new Point(-1, -1)),
        new Particle(new Point( 5,  0), new Point( 1,  0)),
        new Particle(new Point(-6,  0), new Point( 2,  0)),
        new Particle(new Point( 5,  9), new Point( 1, -2)),
        new Particle(new Point(14,  7), new Point(-2,  0)),
        new Particle(new Point(-3,  6), new Point( 2, -1)),
    };

    @Test
    public void solve() {
        Answers a = new Day10().solve(EXAMPLE_INPUT);
        assertEquals(
                "#   #  ###\n" +
                "#   #   # \n" +
                "#   #   # \n" +
                "#####   # \n" +
                "#   #   # \n" +
                "#   #   # \n" +
                "#   #   # \n" +
                "#   #  ###",
                a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void parse() {
        assertArrayEquals(EXAMPLE_PARTICLES, Day10.parse(EXAMPLE_INPUT));
    }
}

package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day22Test {

    static final String EXAMPLE_INPUT = "depth: 510\n" +
            "target: 10,10";

    @Test
    public void solve() {
        Answers a = new Day22().solve(EXAMPLE_INPUT);
        assertEquals("114", a.getPartOne());
        assertEquals("45", a.getPartTwo());
    }

    @Test
    public void solveTaller() {
        Stopwatch w = new Stopwatch();
        Answers a = new Day22().solve("depth: 98765\ntarget: 10,300");
        w.stop();
        System.out.printf("taller: %d ms%n", w.elapsed());
        assertEquals("3288", a.getPartOne());
        assertEquals("404", a.getPartTwo());
    }

    @Test
    public void solveWider() {
        Stopwatch w = new Stopwatch();
        Answers a = new Day22().solve("depth: 43210\ntarget: 300,10");
        w.stop();
        System.out.printf("wider: %d ms%n", w.elapsed());
        assertEquals("3269", a.getPartOne());
        assertEquals("419", a.getPartTwo());
    }

}

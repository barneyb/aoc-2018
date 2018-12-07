package com.barneyb.aoc2018.day07;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day07Test {

    private static final String EXAMPLE_INPUT = "Step C must be finished before step A can begin.\n" +
            "Step C must be finished before step F can begin.\n" +
            "Step A must be finished before step B can begin.\n" +
            "Step A must be finished before step D can begin.\n" +
            "Step B must be finished before step E can begin.\n" +
            "Step D must be finished before step E can begin.\n" +
            "Step F must be finished before step E can begin.";

    private static final Prerequisite[] EXAMPLE_PREREQS = {
            new Prerequisite("A", "C"),
            new Prerequisite("F", "C"),
            new Prerequisite("B", "A"),
            new Prerequisite("D", "A"),
            new Prerequisite("E", "B"),
            new Prerequisite("E", "D"),
            new Prerequisite("E", "F"),
    };

    @Test
    public void solve() {
        Answers a = new Day07().solve(EXAMPLE_INPUT);
        assertEquals("CABDFE", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void parse() {
        assertArrayEquals(EXAMPLE_PREREQS, Day07.parse(EXAMPLE_INPUT));
    }
}

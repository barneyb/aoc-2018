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
            new Prerequisite("C", "A"),
            new Prerequisite("C", "F"),
            new Prerequisite("A", "B"),
            new Prerequisite("A", "D"),
            new Prerequisite("B", "E"),
            new Prerequisite("D", "E"),
            new Prerequisite("F", "E"),
    };

    @Test
    public void solve() {
        Answers a = Day07.solve(EXAMPLE_INPUT, 0, 2);
        assertEquals("CABDFE", a.getPartOne());
        assertEquals("15", a.getPartTwo());
    }

    @Test
    public void parse() {
        assertArrayEquals(EXAMPLE_PREREQS, Day07.parse(EXAMPLE_INPUT));
    }
}

package com.barneyb.aoc2018.day11;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void solveOne() {
        Answers a = new Day11().solve("18");
        assertEquals("33,45", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void solveTwo() {
        Answers a = new Day11().solve("42");
        assertEquals("21,61", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

package com.barneyb.aoc2018.day14;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Test {

    @Test
    public void solveExampleOne() {
        Answers a = new Day14().solve("9");
        assertEquals("5158916779", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void solveExampleTwo() {
        Answers a = new Day14().solve("5");
        assertEquals("0124515891", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void solveExampleThree() {
        Answers a = new Day14().solve("18");
        assertEquals("9251071085", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void solveExampleFour() {
        Answers a = new Day14().solve("2018");
        assertEquals("5941429882", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

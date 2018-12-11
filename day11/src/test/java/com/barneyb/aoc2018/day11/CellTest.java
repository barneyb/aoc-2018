package com.barneyb.aoc2018.day11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellTest {

    @Test
    public void levelOne() {
        assertEquals(4, new Cell(3, 5).level(8));
    }

    @Test
    public void levelTwo() {
        assertEquals(-5, new Cell(122, 79).level(57));
    }

    @Test
    public void levelThree() {
        assertEquals(0, new Cell(217, 196).level(39));
    }

    @Test
    public void levelFour() {
        assertEquals(4, new Cell(101, 153).level(71));
    }

}
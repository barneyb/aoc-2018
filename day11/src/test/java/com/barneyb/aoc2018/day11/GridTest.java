package com.barneyb.aoc2018.day11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridTest {

    @Test
    public void levelOne() {
        assertEquals(4, Grid.level(3, 5, 8));
    }

    @Test
    public void levelTwo() {
        assertEquals(-5, Grid.level(122, 79, 57));
    }

    @Test
    public void levelThree() {
        assertEquals(0, Grid.level(217, 196, 39));
    }

    @Test
    public void levelFour() {
        assertEquals(4, Grid.level(101, 153, 71));
    }

}
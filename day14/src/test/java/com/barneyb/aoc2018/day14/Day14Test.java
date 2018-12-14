package com.barneyb.aoc2018.day14;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Test {

    @Test
    public void solveExampleOne() {
        assertEquals("5158916779", Day14.partOne("9"));
    }

    @Test
    public void solveExampleTwo() {
        assertEquals("0124515891", Day14.partOne("5"));
    }

    @Test
    public void solveExampleThree() {
        assertEquals("9251071085", Day14.partOne("18"));
    }

    @Test
    public void solveExampleFour() {
        assertEquals("5941429882", Day14.partOne("2018"));
    }

    @Test
    public void solveExampleOne_pt2() {
        assertEquals(9, Day14.partTwo("51589"));
    }

    @Test
    public void solveExampleTwo_pt2() {
        assertEquals(5, Day14.partTwo("01245"));
    }

    @Test
    public void solveExampleThree_pt2() {
        assertEquals(18, Day14.partTwo("92510"));
    }

    @Test
    public void solveExampleFour_pt2() {
        assertEquals(2018, Day14.partTwo("59414"));
    }
}

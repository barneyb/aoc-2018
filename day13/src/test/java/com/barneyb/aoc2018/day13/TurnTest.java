package com.barneyb.aoc2018.day13;

import org.junit.Test;

import static com.barneyb.aoc2018.day13.Turn.*;
import static org.junit.Assert.assertEquals;

public class TurnTest {

    @Test
    public void next() {
        assertEquals(STRAIGHT, LEFT.next());
        assertEquals(RIGHT, STRAIGHT.next());
        assertEquals(LEFT, RIGHT.next());
    }
}
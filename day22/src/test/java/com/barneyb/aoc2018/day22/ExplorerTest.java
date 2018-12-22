package com.barneyb.aoc2018.day22;

import org.junit.Test;

import static com.barneyb.aoc2018.day22.Day22Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertEquals;

public class ExplorerTest {

    @Test
    public void getBound() {
        Map m = Map.parse(EXAMPLE_INPUT);
        Explorer e = new Explorer(m);
        assertEquals(97, e.getBound());
    }
}
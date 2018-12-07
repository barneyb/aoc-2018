package com.barneyb.aoc2018.day06;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClosestPlotTest {

    @Test
    public void expansion() {
        assertEquals("aaaaa.ccc\n" +
                "aAaaa.ccc\n" +
                "aaaddeccc\n" +
                "aadddeccC\n" +
                "..dDdeecc\n" +
                "bb.deEeec\n" +
                "bBb.eeee.\n" +
                "bbb.eeeff\n" +
                "bbb.eefff\n" +
                "bbb.ffffF\n",
                new ClosestPlot(Day06Test.EXAMPLE_POINTS).toString());
    }
}
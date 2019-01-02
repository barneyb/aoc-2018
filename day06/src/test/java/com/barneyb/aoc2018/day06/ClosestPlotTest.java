package com.barneyb.aoc2018.day06;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClosestPlotTest {

    @Test
    public void expansion() {
        assertEquals(
                "Aaaa.ccc\n" +
                "aaddeccc\n" +
                "adddeccC\n" +
                ".dDdeecc\n" +
                "b.deEeec\n" +
                "Bb.eeee.\n" +
                "bb.eeeff\n" +
                "bb.eefff\n" +
                "bb.ffffF\n",
                new ClosestPlot(new Plotter(Day06Test.EXAMPLE_POINTS)).toString());
    }
}
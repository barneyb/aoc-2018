package com.barneyb.aoc2018.day01;

import org.junit.Test;

import static com.barneyb.aoc2018.day01.Day01Test.EXAMPLE;
import static org.junit.Assert.assertEquals;

public class ReadoutTest {

    @Test
    public void firstRepeat() {
        assertEquals(2, new Readout(EXAMPLE).firstRepeat());
        assertEquals(0, new Readout(new int[] {+1, -1}).firstRepeat());
        assertEquals(10, new Readout(new int[] {+3, +3, +4, -2, -4}).firstRepeat());
        assertEquals(5, new Readout(new int[] {-6, +3, +8, +5, -6}).firstRepeat());
        assertEquals(14, new Readout(new int[] {+7, +7, -2, -7, -4}).firstRepeat());
    }

}
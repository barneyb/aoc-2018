package com.barneyb.aoc2018.day01;

import com.barneyb.aoc2018.api.impl.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    private static final int[] EXAMPLE = new int[] {+1, -2, +3, +1};

    @Test
    public void solve() {
        Answers a = new Day01().solve("1\n+2\n-3");
        assertEquals("0", a.getPartOne());
        assertEquals("0", a.getPartTwo());
    }

    @Test
    public void firstRepeat() {
        assertEquals(2, new Day01().firstRepeat(EXAMPLE));
        assertEquals(0, new Day01().firstRepeat(new int[] {+1, -1}));
        assertEquals(10, new Day01().firstRepeat(new int[] {+3, +3, +4, -2, -4}));
        assertEquals(5, new Day01().firstRepeat(new int[] {-6, +3, +8, +5, -6}));
        assertEquals(14, new Day01().firstRepeat(new int[] {+7, +7, -2, -7, -4}));
    }

}

package com.barneyb.aoc2018.day02;

import com.barneyb.aoc2018.api.Day;
import com.barneyb.aoc2018.api.impl.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    @Test
    public void solve() {
        Answers a = new Day02().solve("  cat  ");
        assertEquals("7", a.getPartOne());
        assertEquals("3", a.getPartTwo());
    }

}

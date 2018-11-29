package com.barneyb.aoc2018.day00;

import com.barneyb.aoc2018.api.Day;
import com.barneyb.aoc2018.api.impl.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day00Test {

    @Test
    public void solve() {
        Answers a = new Day00().solve("  cat  ");
        assertEquals("7", a.getPartOne());
        assertEquals("3", a.getPartTwo());
    }

    @Test
    public void day() {
        Day d = new Day00();
        d.setInput("  cat  ");
        assertEquals("7", d.getPartOne());
        assertEquals("3", d.getPartTwo());
    }

}
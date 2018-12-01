package com.barneyb.aoc2018.day01;

import com.barneyb.aoc2018.api.Day;
import com.barneyb.aoc2018.api.impl.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    @Test
    public void solve() {
        Answers a = new Day01().solve("  cat  ");
        assertEquals("7", a.getPartOne());
        assertEquals("3", a.getPartTwo());
    }

}

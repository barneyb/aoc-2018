package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.api.Day;
import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day04Test {

    @Test
    public void solve() {
        Answers a = new Day04().solve("  cat  ");
        assertEquals("7", a.getPartOne());
        assertEquals("3", a.getPartTwo());
    }

}

package com.barneyb.aoc2018.day25;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day25Test {

    static final String EXAMPLE_INPUT = "  cat  ";

    @Test
    public void solve() {
        Day25 d = new Day25();
        Answers a = d.solve("0,0,0,0\n" +
                "3,0,0,0\n" +
                "0,3,0,0\n" +
                "0,0,3,0\n" +
                "0,0,0,3\n" +
                "0,0,0,6\n" +
                "9,0,0,0\n" +
                "12,0,0,0");
        assertEquals("2", a.getPartOne());
        assertEquals("-", a.getPartTwo());

        a = d.solve("-1,2,2,0\n" +
                "0,0,2,-2\n" +
                "0,0,0,-2\n" +
                "-1,2,0,0\n" +
                "-2,-2,-2,2\n" +
                "3,0,2,-1\n" +
                "-1,3,2,2\n" +
                "-1,0,-1,0\n" +
                "0,2,1,-2\n" +
                "3,0,0,0");
        assertEquals("4", a.getPartOne());
        assertEquals("-", a.getPartTwo());

        a = d.solve("1,-1,0,1\n" +
                "2,0,-1,0\n" +
                "3,2,-1,0\n" +
                "0,0,3,1\n" +
                "0,0,-1,-1\n" +
                "2,3,-2,0\n" +
                "-2,2,0,0\n" +
                "2,-2,0,-1\n" +
                "1,-1,0,-1\n" +
                "3,2,0,2");
        assertEquals("3", a.getPartOne());
        assertEquals("-", a.getPartTwo());

        a = d.solve("1,-1,-1,-2\n" +
                "-2,-2,0,1\n" +
                "0,2,1,3\n" +
                "-2,3,-2,1\n" +
                "0,2,3,-2\n" +
                "-1,-1,1,-2\n" +
                "0,-2,-1,0\n" +
                "-2,2,3,-1\n" +
                "1,2,2,0\n" +
                "-1,-2,0,-2");
        assertEquals("8", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

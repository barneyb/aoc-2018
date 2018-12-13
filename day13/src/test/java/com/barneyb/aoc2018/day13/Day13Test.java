package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Test {

    static final String EXAMPLE_INPUT =
            "/->-\\        \n" +
            "|   |  /----\\\n" +
            "| /-+--+-\\  |\n" +
            "| | |  | v  |\n" +
            "\\-+-/  \\-+--/\n" +
            "  \\------/   ";
    static final String EXAMPLE_TWO_INPUT =
            "/>-<\\  \n" +
            "|   |  \n" +
            "| /<+-\\\n" +
            "| | | v\n" +
            "\\>+</ |\n" +
            "  |   ^\n" +
            "  \\<->/";

    @Test
    public void solve() {
        Answers a = new Day13().solve(EXAMPLE_INPUT);
        assertEquals("7,3", a.getPartOne());
        a = new Day13().solve(EXAMPLE_TWO_INPUT);
        assertEquals("6,4", a.getPartTwo());
    }

}

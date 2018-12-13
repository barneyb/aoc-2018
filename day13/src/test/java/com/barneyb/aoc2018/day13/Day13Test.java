package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Resources;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Test {

    static final String EXAMPLE_INPUT = Resources.asText("example.txt");

    static final Cart[] EXAMPLE_CARTS = {
            new Cart('A', new Point(2, 0), '-', Dir.EAST),
            new Cart('B', new Point(9, 3), '|', Dir.SOUTH)
    };

    static final String[] EXAMPLE_GRID = {
            // this looks janky cuzza escapes
            "/-A-\\",
            "|   |  /----\\",
            "| /-+--+-\\  |",
            "| | |  | B  |",
            "\\-+-/  \\-+--/",
            "  \\------/"
    };

    @Test
    public void solve() {
        Answers a = new Day13().solve(EXAMPLE_INPUT);
        assertEquals("7,3", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

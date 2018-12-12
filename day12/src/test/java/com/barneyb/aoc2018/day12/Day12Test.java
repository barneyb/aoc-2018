package com.barneyb.aoc2018.day12;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Test {

    private static final String EXAMPLE_INPUT = "initial state: #..#.#..##......###...###\n" +
            "\n" +
            "...## => #\n" +
            "..#.. => #\n" +
            ".#... => #\n" +
            ".#.#. => #\n" +
            ".#.## => #\n" +
            ".##.. => #\n" +
            ".#### => #\n" +
            "..... => .\n" + // not part of the example!
            "#.#.# => #\n" +
            "#.### => #\n" +
            "##.#. => #\n" +
            "##.## => #\n" +
            "###.. => #\n" +
            "###.# => #\n" +
            "####. => #\n";

    @Test
    public void solve() {
        Answers a = new Day12().solve(EXAMPLE_INPUT);
        assertEquals("325", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}

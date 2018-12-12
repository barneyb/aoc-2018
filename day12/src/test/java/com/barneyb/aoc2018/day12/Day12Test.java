package com.barneyb.aoc2018.day12;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Bag;
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

    private static final Bag<String> EXAMPLE_GROWERS = new Bag<>();
    static {
        EXAMPLE_GROWERS.add("...##");
        EXAMPLE_GROWERS.add("..#..");
        EXAMPLE_GROWERS.add(".#...");
        EXAMPLE_GROWERS.add(".#.#.");
        EXAMPLE_GROWERS.add(".#.##");
        EXAMPLE_GROWERS.add(".##..");
        EXAMPLE_GROWERS.add(".####");
        EXAMPLE_GROWERS.add("#.#.#");
        EXAMPLE_GROWERS.add("#.###");
        EXAMPLE_GROWERS.add("##.#.");
        EXAMPLE_GROWERS.add("##.##");
        EXAMPLE_GROWERS.add("###..");
        EXAMPLE_GROWERS.add("###.#");
        EXAMPLE_GROWERS.add("####.");
    }

    @Test
    public void solve() {
        Answers a = new Day12().solve(EXAMPLE_INPUT);
        assertEquals("325", a.getPartOne());
        assertEquals("999999999374", a.getPartTwo());
    }

    @Test
    public void getPotSum() {
        assertEquals(999374, Day12.getPotSum(
                "#..#.#..##......###...###",
                EXAMPLE_GROWERS,
                50000));
    }
}

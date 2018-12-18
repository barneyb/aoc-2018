package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day18Test {

    static final String EXAMPLE_INPUT =
            ".#.#...|#.\n" +
            ".....#|##|\n" +
            ".|..|...#.\n" +
            "..|#.....#\n" +
            "#.#|||#|#|\n" +
            "...#.||...\n" +
            ".|....|...\n" +
            "||...#|.#|\n" +
            "|.||||..|.\n" +
            "...#.|..|.";

    static final String EXAMPLE_10_SECONDS =
            ".||##.....\n" +
            "||###.....\n" +
            "||##......\n" +
            "|##.....##\n" +
            "|##.....##\n" +
            "|##....##|\n" +
            "||##.####|\n" +
            "||#####|||\n" +
            "||||#|||||\n" +
            "||||||||||";

    @Test
    public void solve() {
        Answers a = new Day18().solve(EXAMPLE_INPUT);
        assertEquals("1147", a.getPartOne());
        assertEquals("0", a.getPartTwo());
    }

}

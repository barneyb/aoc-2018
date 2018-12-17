package com.barneyb.aoc2018.day17;

import org.junit.Test;

import static com.barneyb.aoc2018.day17.Day17Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertEquals;

public class EarthTest {

    @Test
    public void strings() {
        Earth e = new Earth(Day17.parse(EXAMPLE_INPUT));
        assertEquals(
                "......+.......\n" +
                "............#.\n" +
                ".#..#.......#.\n" +
                ".#..#..#......\n" +
                ".#..#..#......\n" +
                ".#.....#......\n" +
                ".#.....#......\n" +
                ".#######......\n" +
                "..............\n" +
                "..............\n" +
                "....#.....#...\n" +
                "....#.....#...\n" +
                "....#.....#...\n" +
                "....#######...",
                e.toString());
    }

    @Test
    public void example() {
        Earth e = new Earth(Day17.parse(EXAMPLE_INPUT));
        e.runWater();
        assertEquals(
                "......+.......\n" +
                "......|.....#.\n" +
                ".#..#||||...#.\n" +
                ".#..#~~#|.....\n" +
                ".#..#~~#|.....\n" +
                ".#~~~~~#|.....\n" +
                ".#~~~~~#|.....\n" +
                ".#######|.....\n" +
                "........|.....\n" +
                "...|||||||||..\n" +
                "...|#~~~~~#|..\n" +
                "...|#~~~~~#|..\n" +
                "...|#~~~~~#|..\n" +
                "...|#######|..",
                e.toString());
    }
}
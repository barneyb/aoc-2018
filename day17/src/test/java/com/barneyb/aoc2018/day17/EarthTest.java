package com.barneyb.aoc2018.day17;

import org.junit.Test;

import static com.barneyb.aoc2018.day17.Day17Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertEquals;

public class EarthTest {

    @Test
    public void strings() {
        Earth e = new Earth(Day17.parse(EXAMPLE_INPUT));
        String expected =
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
                "....#######...";
        assertEquals(expected, e.toString());
        e = Earth.parse(e.toString());
        assertEquals(expected, e.toString());
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

    @Test
    public void innerContainer() {
        Earth e = Earth.parse(
                ".........+........\n" +
                ".#..............#.\n" +
                "..............#...\n" +
                "....#.........#...\n" +
                "....#~~~#.#~~~#...\n" +
                "....###########...\n" +
                ".#................"
        );
        e.runWater();
        assertEquals(
                ".........+........\n" +
                ".#.......|......#.\n" +
                "...|||||||||||#...\n" +
                "...|#~~~~~~~~~#...\n" +
                "...|#~~~#~#~~~#...\n" +
                "...|###########...\n" +
                ".#.|..............",
                e.toString());
    }

    @Test
    public void runOntoEdge() {
        Earth e = Earth.parse(
                "........+.............\n" +
                "......................\n" +
                "........#..#..........\n" +
                "........#..#..........\n" +
                "........#..#.......#..\n" +
                "...#....#..#.......#..\n" +
                "...#################.."
        );
        e.runWater();
        System.out.println(e.toString(true));
        assertEquals(
                "......+............\n" +
                ".....||||||........\n" +
                ".....|#~~#|........\n" +
                ".....|#~~#|||||||||\n" +
                "||||||#~~#~~~~~~~#|\n" +
                "|#~~~~#~~#~~~~~~~#|\n" +
                "|#################|",
                e.toString());
    }
}
package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.Histogram;
import com.barneyb.aoc2018.util.Point;
import org.junit.Test;

import static com.barneyb.aoc2018.day18.Day18Test.EXAMPLE_10_SECONDS;
import static com.barneyb.aoc2018.day18.Day18Test.EXAMPLE_INPUT;
import static com.barneyb.aoc2018.day18.Map.*;
import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void strings() {
        Map m = Map.parse(EXAMPLE_INPUT);
        assertEquals(EXAMPLE_INPUT, m.toString());
    }

    @Test
    public void openNext() {
        Point p = new Point(1, 1);
        assertEquals(OPEN, Map.parse("...\n...\n...").next(p));
        assertEquals(OPEN, Map.parse("|..\n...\n...").next(p));
        assertEquals(OPEN, Map.parse("||.\n...\n...").next(p));
        assertEquals(TREES, Map.parse("|||\n...\n...").next(p));
        assertEquals(TREES, Map.parse("|||\n|..\n...").next(p));
        assertEquals(TREES, Map.parse("|||\n|.|\n...").next(p));
        assertEquals(TREES, Map.parse("|||\n|.|\n|..").next(p));
        assertEquals(TREES, Map.parse("|||\n|.|\n||.").next(p));
        assertEquals(TREES, Map.parse("|||\n|.|\n|||").next(p));
    }

    @Test
    public void treesNext() {
        Point p = new Point(1, 1);
        assertEquals(TREES, Map.parse("...\n.|.\n...").next(p));
        assertEquals(TREES, Map.parse("#..\n.|.\n...").next(p));
        assertEquals(TREES, Map.parse("##.\n.|.\n...").next(p));
        assertEquals(LUMBERYARD, Map.parse("###\n.|.\n...").next(p));
        assertEquals(LUMBERYARD, Map.parse("###\n#|.\n...").next(p));
        assertEquals(LUMBERYARD, Map.parse("###\n#|#\n...").next(p));
        assertEquals(LUMBERYARD, Map.parse("###\n#|#\n#..").next(p));
        assertEquals(LUMBERYARD, Map.parse("###\n#|#\n##.").next(p));
        assertEquals(LUMBERYARD, Map.parse("###\n#|#\n###").next(p));
    }

    @Test
    public void lumberyardNext() {
        Point p = new Point(1, 1);
        assertEquals(OPEN, Map.parse("...\n.#.\n...").next(p));
        assertEquals(OPEN, Map.parse("|..\n.#.\n...").next(p));
        assertEquals(OPEN, Map.parse("#..\n.#.\n...").next(p));
        assertEquals(LUMBERYARD, Map.parse("#|.\n.#.\n...").next(p));
        assertEquals(OPEN, Map.parse("##.\n.#.\n...").next(p));
    }

    @Test
    public void buildHist() {
        Point p = new Point(1, 1);
        Histogram<Character> h = Map.parse("...\n.#.\n...").buildHist(p);
        assertEquals(8, h.get(OPEN).intValue());
        assertEquals(0, h.get(TREES).intValue());
        assertEquals(0, h.get(LUMBERYARD).intValue());
    }

    @Test
    public void tick() {
        Map m = Map.parse(EXAMPLE_INPUT);
        m.tick(); // 1
        assertEquals(
                ".......##.\n" +
                "......|###\n" +
                ".|..|...#.\n" +
                "..|#||...#\n" +
                "..##||.|#|\n" +
                "...#||||..\n" +
                "||...|||..\n" +
                "|||||.||.|\n" +
                "||||||||||\n" +
                "....||..|.",
                m.toString());
        m.tick(); // 2
        assertEquals(
                ".......#..\n" +
                "......|#..\n" +
                ".|.|||....\n" +
                "..##|||..#\n" +
                "..###|||#|\n" +
                "...#|||||.\n" +
                "|||||||||.\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                ".|||||||||",
                m.toString());
        m.tick(); // 3
        assertEquals(
                ".......#..\n" +
                "....|||#..\n" +
                ".|.||||...\n" +
                "..###|||.#\n" +
                "...##|||#|\n" +
                ".||##|||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||",
                m.toString());
        m.tick(); // 4
        assertEquals(
                ".....|.#..\n" +
                "...||||#..\n" +
                ".|.#||||..\n" +
                "..###||||#\n" +
                "...###||#|\n" +
                "|||##|||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||",
                m.toString());
        m.tick(); // 5
        assertEquals(
                "....|||#..\n" +
                "...||||#..\n" +
                ".|.##||||.\n" +
                "..####|||#\n" +
                ".|.###||#|\n" +
                "|||###||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||",
                m.toString());
        m.tick(); // 6
        assertEquals(
                "...||||#..\n" +
                "...||||#..\n" +
                ".|.###|||.\n" +
                "..#.##|||#\n" +
                "|||#.##|#|\n" +
                "|||###||||\n" +
                "||||#|||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||",
                m.toString());
        m.tick(); // 7
        assertEquals(
                "...||||#..\n" +
                "..||#|##..\n" +
                ".|.####||.\n" +
                "||#..##||#\n" +
                "||##.##|#|\n" +
                "|||####|||\n" +
                "|||###||||\n" +
                "||||||||||\n" +
                "||||||||||\n" +
                "||||||||||",
                m.toString());
        m.tick(); // 8
        assertEquals(
                "..||||##..\n" +
                "..|#####..\n" +
                "|||#####|.\n" +
                "||#...##|#\n" +
                "||##..###|\n" +
                "||##.###||\n" +
                "|||####|||\n" +
                "||||#|||||\n" +
                "||||||||||\n" +
                "||||||||||",
                m.toString());
        m.tick(); // 9
        assertEquals(
                "..||###...\n" +
                ".||#####..\n" +
                "||##...##.\n" +
                "||#....###\n" +
                "|##....##|\n" +
                "||##..###|\n" +
                "||######||\n" +
                "|||###||||\n" +
                "||||||||||\n" +
                "||||||||||",
                m.toString());
        m.tick(); // 10
        assertEquals(
                EXAMPLE_10_SECONDS,
                m.toString());
    }

    @Test
    public void tickN() {
        Map m = Map.parse(EXAMPLE_INPUT);
        m.tick(10);
        assertEquals(
                EXAMPLE_10_SECONDS,
                m.toString());
    }

}
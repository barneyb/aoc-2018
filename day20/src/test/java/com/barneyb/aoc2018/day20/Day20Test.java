package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20Test {

    static final String EXAMPLE_INPUT = "  cat  ";

    @Test
    public void solve() {
        Answers a = new Day20().solve(EXAMPLE_INPUT);
        assertEquals("7", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void exampleOne_a() {
        String input = "^WNE$";
        assertEquals(
                "#####\n" +
                "#.|.#\n" +
                "#-###\n" +
                "#.|X#\n" +
                "#####",
                Day20.plotDoors(Day20.findDoors(input)));
//        assertEquals(3, Day20.partOne(input));
    }

    @Test
    public void exampleOne_b() {
        String input = "^ENWWW(NEEE|SSE(EE|N))$";
        assertEquals(
                "#########\n" +
                "#.|.|.|.#\n" +
                "#-#######\n" +
                "#.|.|.|.#\n" +
                "#-#####-#\n" +
                "#.#.#X|.#\n" +
                "#-#-#####\n" +
                "#.|.|.|.#\n" +
                "#########",
                Day20.plotDoors(Day20.findDoors(input)));
//        assertEquals(10, Day20.partOne(input));
    }

    @Test
    public void exampleOne_c() {
        String input = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
        assertEquals(
                "###########\n" +
                "#.|.#.|.#.#\n" +
                "#-###-#-#-#\n" +
                "#.|.|.#.#.#\n" +
                "#-#####-#-#\n" +
                "#.#.#X|.#.#\n" +
                "#-#-#####-#\n" +
                "#.#.|.|.|.#\n" +
                "#-###-###-#\n" +
                "#.|.|.#.|.#\n" +
                "###########",
                Day20.plotDoors(Day20.findDoors(input)));
//        assertEquals(18, Day20.partOne(input));
    }

    @Test
    public void exampleOne_d() {
        String input = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
        assertEquals(
                "#############\n" +
                "#.|.|.|.|.|.#\n" +
                "#-#####-###-#\n" +
                "#.#.|.#.#.#.#\n" +
                "#-#-###-#-#-#\n" +
                "#.#.#.|.#.|.#\n" +
                "#-#-#-#####-#\n" +
                "#.#.#.#X|.#.#\n" +
                "#-#-#-###-#-#\n" +
                "#.|.#.|.#.#.#\n" +
                "###-#-###-#-#\n" +
                "#.|.#.|.|.#.#\n" +
                "#############",
                Day20.plotDoors(Day20.findDoors(input)));
//        assertEquals(23, Day20.partOne(input));
    }

    @Test
    public void exampleOne_e() {
        String input = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
        assertEquals(
                "###############\n" +
                "#.|.|.|.#.|.|.#\n" +
                "#-###-###-#-#-#\n" +
                "#.|.#.|.|.#.#.#\n" +
                "#-#########-#-#\n" +
                "#.#.|.|.|.|.#.#\n" +
                "#-#-#########-#\n" +
                "#.#.#.|X#.|.#.#\n" +
                "###-#-###-#-#-#\n" +
                "#.|.#.#.|.#.|.#\n" +
                "#-###-#####-###\n" +
                "#.|.#.|.|.#.#.#\n" +
                "#-#-#####-#-#-#\n" +
                "#.#.|.|.|.#.|.#\n" +
                "###############",
                Day20.plotDoors(Day20.findDoors(input)));
//        assertEquals(31, Day20.partOne(input));
    }
}

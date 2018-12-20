package com.barneyb.aoc2018.day20;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void exampleOne_a() {
        Map m = Map.parse("^WNE$");
        assertEquals(
                "#####\n" +
                "#.|.#\n" +
                "#-###\n" +
                "#.|X#\n" +
                "#####",
                m.toString());
    }

    @Test
    public void exampleOne_b() {
        Map m = Map.parse("^ENWWW(NEEE|SSE(EE|N))$");
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
                m.toString());
    }

    @Test
    public void exampleOne_c() {
        Map m = Map.parse("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$");
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
                m.toString());
    }

    @Test
    public void exampleOne_d() {
        Map m = Map.parse("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$");
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
                m.toString());
    }

    @Test
    public void exampleOne_e() {
        Map m = Map.parse("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$");
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
                m.toString());
    }
}

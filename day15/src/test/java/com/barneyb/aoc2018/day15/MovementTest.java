package com.barneyb.aoc2018.day15;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovementTest {

    @Test
    public void movement() {
        Movement movement = new Movement();
        Map map = Map.parse(
                "#########\n" +
                        "#G..G..G#\n" +
                        "#.......#\n" +
                        "#.......#\n" +
                        "#G..E..G#\n" +
                        "#.......#\n" +
                        "#.......#\n" +
                        "#G..G..G#\n" +
                        "#########");
        movement.doRound(map);
        assertEquals(
                "#########\n" +
                        "#.G...G.#\n" +
                        "#...G...#\n" +
                        "#...E..G#\n" +
                        "#.G.....#\n" +
                        "#.......#\n" +
                        "#G..G..G#\n" +
                        "#.......#\n" +
                        "#########",
                map.toString());
        movement.doRound(map);
        assertEquals(
                "#########\n" +
                        "#..G.G..#\n" +
                        "#...G...#\n" +
                        "#.G.E.G.#\n" +
                        "#.......#\n" +
                        "#G..G..G#\n" +
                        "#.......#\n" +
                        "#.......#\n" +
                        "#########",
                map.toString());
        movement.doRound(map);
        assertEquals(
                "#########\n" +
                        "#.......#\n" +
                        "#..GGG..#\n" +
                        "#..GEG..#\n" +
                        "#G..G...#\n" +
                        "#......G#\n" +
                        "#.......#\n" +
                        "#.......#\n" +
                        "#########",
                map.toString());
        // at this point, no more moves are available
    }

}
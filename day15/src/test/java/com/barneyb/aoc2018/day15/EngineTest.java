package com.barneyb.aoc2018.day15;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngineTest {

    @Test
    public void paint() {
        Map m = Map.parse(
                "#######\n" +
                "#.E...#\n" +
                "#.....#\n" +
                "#...G.#\n" +
                "#######");
        Engine e = new Engine(m);
        e.doRound(false);
    }

    @Test
    public void movement() {
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
        assertEquals(
                "#########\n" +
                "#A..B..C#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#D..a..E#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#F..G..H#\n" +
                "#########",
                map.toString());
        Engine e = new Engine(map);
        e.doRound(false);
        assertEquals(
                "#########\n" +
                "#.A...C.#\n" +
                "#...B...#\n" +
                "#...a..E#\n" +
                "#.D.....#\n" +
                "#.......#\n" +
                "#F..G..H#\n" +
                "#.......#\n" +
                "#########",
                map.toString());
        e.doRound(false);
        assertEquals(
                "#########\n" +
                "#..A.C..#\n" +
                "#...B...#\n" +
                "#.D.a.E.#\n" +
                "#.......#\n" +
                "#F..G..H#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########",
                map.toString());
        e.doRound(false);
        assertEquals(
                "#########\n" +
                "#.......#\n" +
                "#..ABC..#\n" +
                "#..DaE..#\n" +
                "#F..G...#\n" +
                "#......H#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########",
                map.toString());
        // at this point, no more moves are available w/out a kill, so "no-op"
        e.doRound(false);
        assertEquals(
                "#########\n" +
                "#.......#\n" +
                "#..ABC..#\n" +
                "#..DaE..#\n" +
                "#F..G...#\n" +
                "#......H#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########",
                map.toString());
    }

}
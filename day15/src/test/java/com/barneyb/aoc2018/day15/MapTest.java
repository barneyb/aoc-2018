package com.barneyb.aoc2018.day15;

import org.junit.Test;

import java.util.Iterator;

import static com.barneyb.aoc2018.day15.Day15Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MapTest {

    @Test
    public void strings() {
        Map m = Map.parse(EXAMPLE_INPUT);
        assertEquals(
                "#######\n" +
                "#.A...#\n" +
                "#...aB#\n" +
                "#.#.#C#\n" +
                "#..D#b#\n" +
                "#.....#\n" +
                "#######",
                m.toString());
        assertEquals(
                "#######\n" +
                "#.A...# A(300)\n" +
                "#...aB# a(300) B(300)\n" +
                "#.#.#C# C(300)\n" +
                "#..D#b# D(300) b(300)\n" +
                "#.....#\n" +
                "#######",
                m.toString(true));
    }

    @Test
    public void unitOrder() {
        Map m = Map.parse(
                "#######\n" +
                "#.G.E.#\n" +
                "#E.G.E#\n" +
                "#.G.E.#\n" +
                "#######");
        assertEquals(
                "#######\n" +
                "#.A.a.#\n" +
                "#b.B.c#\n" +
                "#.C.d.#\n" +
                "#######",
                m.toString()
        );
        Iterator<Unit> itr = m.livingUnits().iterator();
        assertEquals('A', itr.next().label());
        assertEquals('a', itr.next().label());
        assertEquals('b', itr.next().label());
        assertEquals('B', itr.next().label());
        assertEquals('c', itr.next().label());
        assertEquals('C', itr.next().label());
        assertEquals('d', itr.next().label());
        assertFalse(itr.hasNext());
    }
}
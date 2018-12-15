package com.barneyb.aoc2018.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author bboisvert
 */
public class PointTest {

    @Test
    public void plus() {
        Point p = new Point(1,2);
        p = p.plus(new Point(3, 4));
        assertEquals(4, p.x);
        assertEquals(6, p.y);
    }

    @Test
    public void minus() {
        Point p = new Point(1,2);
        p = p.minus(new Point(4, 3));
        assertEquals(-3, p.x);
        assertEquals(-1, p.y);
    }

    @Test
    public void times() {
        Point p = new Point(1,2);
        p = p.times(3);
        assertEquals(3, p.x);
        assertEquals(6, p.y);
    }

    @Test
    public void compareTo() {
        Point origin = new Point(0, 0);
        assertTrue(origin.compareTo(origin) == 0);
        assertTrue(new Point(1, 0).compareTo(origin) > 0);
        assertTrue(new Point(1, 1).compareTo(origin) > 0);
        assertTrue(new Point(0, 1).compareTo(origin) > 0);
        assertTrue(new Point(-1, 1).compareTo(origin) > 0);
        assertTrue(new Point(-1, 0).compareTo(origin) < 0);
        assertTrue(new Point(-1, -1).compareTo(origin) < 0);
        assertTrue(new Point(0, -1).compareTo(origin) < 0);
        assertTrue(new Point(1, -1).compareTo(origin) < 0);
    }

    @Test
    public void adjacent() {
        Point p = new Point(0, 0);
        assertTrue(p.adjacent(new Point(0, -1)));
        assertTrue(p.adjacent(new Point(0, 1)));
        assertTrue(p.adjacent(new Point(1, 0)));
        assertTrue(p.adjacent(new Point(-1, 0)));

        assertFalse(p.adjacent(new Point(-1, -1)));
        assertFalse(p.adjacent(new Point(-1, 1)));
        assertFalse(p.adjacent(new Point(1, -1)));
        assertFalse(p.adjacent(new Point(-1, -1)));
    }
}
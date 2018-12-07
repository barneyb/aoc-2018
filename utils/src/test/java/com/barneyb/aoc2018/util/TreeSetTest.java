package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeSetTest {

    @Test
    public void allInOne() {
        TreeSet<Integer> s = new TreeSet<>();
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
        s.add(3);
        s.add(1);
        s.add(3);
        assertTrue(s.contains(1));
        assertFalse(s.contains(2));
        assertFalse(s.isEmpty());
        assertEquals(2, s.size());
        s.clear();
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
    }

    @Test
    public void iterator() {
        TreeSet<Integer> s = new TreeSet<>();
        s.add(1);
        s.add(3);
        s.add(5);
        Iterator<Integer> itr = s.iterator();
        assertTrue(itr.hasNext());
        assertTrue(s.contains(itr.next()));
        assertTrue(itr.hasNext());
        assertTrue(s.contains(itr.next()));
        assertTrue(itr.hasNext());
        assertTrue(s.contains(itr.next()));
        assertFalse(itr.hasNext());
    }

    @Test
    public void toString_() {
        TreeSet<Integer> s = new TreeSet<>();
        assertEquals("[]", s.toString());
        s.add(3);
        s.add(7);
        s.add(1);
        assertEquals("[1,3,7]", s.toString());
    }

    @Test
    public void deletion() {
        TreeSet<Integer> s = new TreeSet<>();
        assertEquals(0, s.size());
        assertEquals(0, Utils.iteratorLength(s));
        s.add(1);
        s.add(2);
        s.add(3);
        assertEquals(3, s.size());
        assertEquals(3, Utils.iteratorLength(s));
        s.delete(2);
        assertEquals(2, s.size());
        assertEquals(2, s.size());
        s.delete(99999);
        assertEquals(2, Utils.iteratorLength(s));
        assertEquals(2, Utils.iteratorLength(s));
    }
}
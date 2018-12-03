package com.barneyb.aoc2018.day02;

import org.junit.Test;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HistogramTest {

    @Test
    public void everythingExists() {
        Histogram<Integer> h = new Histogram<>();
        assertTrue(h.contains(12345));
        assertEquals(valueOf(0), h.get(5432));
    }

    @Test
    public void fromString_ordered() {
        Histogram<Character> h = Histogram.fromString("abbccc");
        assertEquals(valueOf(1), h.get('a'));
        assertEquals(valueOf(2), h.get('b'));
        assertEquals(valueOf(3), h.get('c'));
    }

    @Test
    public void fromString_mixed() {
        Histogram<Character> h = Histogram.fromString("cbacbc");
        assertEquals(valueOf(1), h.get('a'));
        assertEquals(valueOf(2), h.get('b'));
        assertEquals(valueOf(3), h.get('c'));
    }

}
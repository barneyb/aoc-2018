package com.barneyb.aoc2018.day02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HistogramTest {

    @Test
    public void everythingExists() {
        Histogram<Integer> h = new Histogram<Integer>();
        assertTrue(h.contains(12345));
        assertEquals(0, h.get(5432).intValue());
    }

    @Test
    public void fromString_ordered() {
        Histogram<Character> h = Histogram.fromString("abbccc");
        assertEquals(1, h.get('a').intValue());
        assertEquals(2, h.get('b').intValue());
        assertEquals(3, h.get('c').intValue());
    }

    @Test
    public void fromString_mixed() {
        Histogram<Character> h = Histogram.fromString("cbacbc");
        assertEquals(1, h.get('a').intValue());
        assertEquals(2, h.get('b').intValue());
        assertEquals(3, h.get('c').intValue());
    }

}
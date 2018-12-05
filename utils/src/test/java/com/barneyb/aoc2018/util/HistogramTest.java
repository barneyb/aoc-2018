package com.barneyb.aoc2018.util;

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
    public void ofCharacters_ordered() {
        Histogram<Character> h = Histogram.ofCharacters("abbccc");
        assertEquals(valueOf(1), h.get('a'));
        assertEquals(valueOf(2), h.get('b'));
        assertEquals(valueOf(3), h.get('c'));
    }

    @Test
    public void ofCharacters_mixed() {
        Histogram<Character> h = Histogram.ofCharacters("cbacbc");
        assertEquals(valueOf(1), h.get('a'));
        assertEquals(valueOf(2), h.get('b'));
        assertEquals(valueOf(3), h.get('c'));
    }

    @Test
    public void count() {
        Histogram<Character> h = new Histogram<>();
        h.count('c');
        h.count('b');
        h.count('a');
        h.count('c');
        h.count('b');
        h.count('c');
        assertEquals(valueOf(1), h.get('a'));
        assertEquals(valueOf(2), h.get('b'));
        assertEquals(valueOf(3), h.get('c'));
    }

    @Test
    public void add() {
        Histogram<Character> h = new Histogram<>();
        h.add('b', 2);
        h.add('c', 3);
        h.add('a', 1);
        assertEquals(valueOf(1), h.get('a'));
        assertEquals(valueOf(2), h.get('b'));
        assertEquals(valueOf(3), h.get('c'));
    }

    @Test
    public void bounds() {
        // ties are broken by "lowest key"
        Histogram<Character> h = new Histogram<>();
        h.add('b', 2);
        h.add('c', 3);
        h.add('a', 1);
        assertEquals(Character.valueOf('a'), h.leastFrequent());
        assertEquals(Character.valueOf('c'), h.mostFrequent());
        h.count('a');
        assertEquals(Character.valueOf('a'), h.leastFrequent());
        assertEquals(Character.valueOf('c'), h.mostFrequent());
        h.count('a');
        assertEquals(Character.valueOf('b'), h.leastFrequent());
        assertEquals(Character.valueOf('a'), h.mostFrequent());
    }
}
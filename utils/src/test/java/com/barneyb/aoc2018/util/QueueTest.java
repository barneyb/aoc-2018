package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void allInOne() {
        Queue<String> s = new Queue<>();
        assertEquals(0, s.size());
        assertTrue(s.isEmpty());
        s.enqueue("cat");
        s.enqueue("dog");
        s.enqueue("bat");
        assertEquals(3, s.size());
        assertFalse(s.isEmpty());
        Iterator<String> itr = s.iterator();
        assertTrue(itr.hasNext());
        assertEquals("cat", itr.next());
        assertTrue(itr.hasNext());
        assertEquals("dog", itr.next());
        assertTrue(itr.hasNext());
        assertEquals("bat", itr.next());
        assertFalse(itr.hasNext());
        assertEquals("cat", s.dequeue());
        assertEquals("dog", s.dequeue());
        assertEquals("bat", s.dequeue());
        assertEquals(0, s.size());
        assertTrue(s.isEmpty());
    }

}
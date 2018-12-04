package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void allInOne() {
        Stack<String> s = new Stack<>();
        assertEquals(0, s.size());
        assertTrue(s.isEmpty());
        s.push("cat");
        s.push("dog");
        s.push("bat");
        assertEquals(3, s.size());
        assertFalse(s.isEmpty());
        Iterator<String> itr = s.iterator();
        assertTrue(itr.hasNext());
        assertEquals("bat", itr.next());
        assertTrue(itr.hasNext());
        assertEquals("dog", itr.next());
        assertTrue(itr.hasNext());
        assertEquals("cat", itr.next());
        assertFalse(itr.hasNext());
        assertEquals("bat", s.pop());
        assertEquals("dog", s.pop());
        assertEquals("cat", s.pop());
        assertEquals(0, s.size());
        assertTrue(s.isEmpty());
    }

    @Test
    public void equality() {
        Stack<String> a = new Stack<>(new String[] {
               "cat",
               "dog"
        });
        Stack<String> b = new Stack<>();
        b.push("cat");
        b.push("dog");
        assertEquals(a, b);
    }

}
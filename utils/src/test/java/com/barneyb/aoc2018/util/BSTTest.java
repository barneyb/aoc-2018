package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void allInOne() {
        BST<Integer, Integer> st = new BST<>();
        assertTrue(st.isEmpty());
        assertEquals(0, st.size());
        st.put(3, 4);
        st.put(1, 2);
        st.put(3, 5);
        assertTrue(st.contains(1));
        assertFalse(st.contains(2));
        assertFalse(st.isEmpty());
        assertEquals(2, st.size());
        assertEquals(valueOf(2), st.get(1));
        assertNull(st.get(2));
        assertEquals(valueOf(5), st.get(3));
        st.clear();
        assertTrue(st.isEmpty());
        assertEquals(0, st.size());
    }

    @Test
    public void iterator() {
        BST<Integer, Integer> st = new BST<>();
        st.put(1, 2);
        st.put(3, 4);
        st.put(5, 6);
        Iterator<Integer> itr = st.keys().iterator();
        assertTrue(itr.hasNext());
        assertTrue(st.contains(itr.next()));
        assertTrue(itr.hasNext());
        assertTrue(st.contains(itr.next()));
        assertTrue(itr.hasNext());
        assertTrue(st.contains(itr.next()));
        assertFalse(itr.hasNext());
    }

    @Test
    public void toString_() {
        BST<String, Integer> st = new BST<>();
        assertEquals("{}", st.toString());
        st.put("cat", 3);
        st.put("dog", 7);
        st.put("bat", 1);
        assertEquals("{bat:1,cat:3,dog:7}", st.toString());
    }

}
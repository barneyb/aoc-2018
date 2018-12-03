package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeSetTest {

    @Test
    public void allInOne() {
        TreeSet<Integer> set = new TreeSet<>();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(3);
        set.add(1);
        set.add(3);
        assertTrue(set.contains(1));
        assertFalse(set.contains(2));
        assertFalse(set.isEmpty());
        assertEquals(2, set.size());
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void iterator() {
        TreeSet<Integer> st = new TreeSet<>();
        st.add(1);
        st.add(3);
        st.add(5);
        Iterator<Integer> itr = st.iterator();
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
        TreeSet<Integer> st = new TreeSet<>();
        assertEquals("[]", st.toString());
        st.add(3);
        st.add(7);
        st.add(1);
        assertEquals("[1,3,7]", st.toString());
    }

}
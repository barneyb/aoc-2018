package com.barneyb.aoc2018.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UFTest {

    @Test
    public void doit() {
        UF uf = new UF(10);
        assertEquals(10, uf.components());
        uf.union(0, 1);
        assertEquals(9, uf.components());
        assertFalse(uf.connected(1, 2));
        uf.union(0, 2);
        assertTrue(uf.connected(1, 2));
        uf.union(0, 3);
        uf.union(4, 3);
        uf.union(5, 3);
        uf.union(6, 7);
        uf.union(6, 9);
        assertTrue(uf.connected(5, 0));
        assertTrue(uf.connected(0, 5));
        assertTrue(uf.connected(9, 9));
        assertFalse(uf.connected(5, 9));
        assertEquals(3, uf.components());

        uf.union(1, 2);
        assertEquals(3, uf.components());
    }
}
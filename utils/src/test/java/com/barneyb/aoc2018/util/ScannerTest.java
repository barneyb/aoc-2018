package com.barneyb.aoc2018.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ScannerTest {

    @Test
    public void rest() {
        assertEquals("", new Scanner("").rest());
        assertEquals("cat", new Scanner("cat").rest());
    }

    @Test
    public void skipCount() {
        Scanner s = new Scanner("cat");
        assertSame(s, s.skip(1));
        assertEquals("at", s.rest());
    }

    @Test
    public void skipCountOverflow() {
        Scanner s = new Scanner("cat");
        assertSame(s, s.skip(100));
        assertEquals("", s.rest());
    }

    @Test
    public void skipString() {
        Scanner s = new Scanner("cat");
        assertSame(s, s.skip("c"));
        assertEquals("at", s.rest());
    }

    @Test(expected = NoMatchException.class)
    public void skipStringMiss() {
        Scanner s = new Scanner("cat");
        s.skip("X");
    }

    @Test
    public void readCount() {
        Scanner s = new Scanner("cat");
        assertEquals("c", s.read(1));
        assertEquals("at", s.rest());
    }

    @Test
    public void readCountOverflow() {
        Scanner s = new Scanner("cat");
        assertEquals("cat", s.read(100));
        assertEquals("", s.rest());
    }

    @Test
    public void readInt() {
        Scanner s = new Scanner("123cat");
        assertEquals(123, s.readInt());
        assertEquals("cat", s.rest());
    }

    @Test(expected = NoMatchException.class)
    public void readIntMiss() {
        Scanner s = new Scanner("cat");
        s.readInt();
    }

    @Test
    public void readWord() {
        Scanner s = new Scanner("i eat frogs");
        assertEquals("i", s.readWord());
        assertEquals("eat", s.readWord());
        assertEquals("frogs", s.readWord());
    }

}
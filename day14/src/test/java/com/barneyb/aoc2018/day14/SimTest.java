package com.barneyb.aoc2018.day14;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimTest {

    @Test
    public void tick() {
        Sim s = new Sim();
        assertEquals("(3)[7]", s.toString());
        s.tick();
        assertEquals("(3)[7] 1  0 ", s.toString());
        s.tick();
        assertEquals(" 3  7  1 [0](1) 0 ", s.toString());
        s.tick();
        assertEquals(" 3  7  1  0 [1] 0 (1)", s.toString());
        s.tick();
        assertEquals("(3) 7  1  0  1  0 [1] 2 ", s.toString());
        s.tick();
        assertEquals(" 3  7  1  0 (1) 0  1  2 [4]", s.toString());
        s.tick();
        assertEquals(" 3  7  1 [0] 1  0 (1) 2  4  5 ", s.toString());
        s.tick();
        assertEquals(" 3  7  1  0 [1] 0  1  2 (4) 5  1 ", s.toString());
        s.tick();
        assertEquals(" 3 (7) 1  0  1  0 [1] 2  4  5  1  5 ", s.toString());
        s.tick();
        assertEquals(" 3  7  1  0  1  0  1  2 [4](5) 1  5  8 ", s.toString());
        s.tick();
        assertEquals(" 3 (7) 1  0  1  0  1  2  4  5  1  5  8 [9]", s.toString());
        s.tick();
        assertEquals(" 3  7  1  0  1  0  1 [2] 4 (5) 1  5  8  9  1  6 ", s.toString());
        s.tick();
        s.tick();
        s.tick();
        s.tick();
        assertEquals(" 3  7  1  0 [1] 0  1  2 (4) 5  1  5  8  9  1  6  7  7  9  2 ", s.toString());
    }

}
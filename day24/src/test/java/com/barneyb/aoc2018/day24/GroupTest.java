package com.barneyb.aoc2018.day24;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroupTest {

    @Test
    public void dealDamage() {
        Group g = new Group();
        g.hitPoints(10);
        g.units(10);
        g.dealDamage(75);
        assertEquals(3, g.units());
        assertEquals(10, g.hitPoints());
    }

}
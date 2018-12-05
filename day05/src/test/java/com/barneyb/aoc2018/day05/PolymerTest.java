package com.barneyb.aoc2018.day05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

abstract public class PolymerTest {

    abstract Polymer parse(String s);

    @Test
    public void length() {
        Polymer p = parse("aA");
        assertEquals(2, p.length());
        p.reduce(); // to ""
        assertEquals(0, p.length());
    }

    @Test
    public void parseAndReduce_aA() {
        Polymer p = parse("aA");
        p.reduce();
        assertEquals("", p.toString());
    }

    @Test
    public void parseAndReduce_Aa() {
        Polymer p = parse("Aa");
        p.reduce();
        assertEquals("", p.toString());
    }

    @Test
    public void parseAndReduce_abBA() {
        Polymer p = parse("abBA");
        p.reduce();
        assertEquals("", p.toString());
    }

    @Test
    public void parseAndReduce_abAB() {
        Polymer p = parse("abAB");
        p.reduce();
        assertEquals("abAB", p.toString());
    }

    @Test
    public void parseAndReduce_aabAAB() {
        Polymer p = parse("aabAAB");
        p.reduce();
        assertEquals("aabAAB", p.toString());
    }

    @Test
    public void parseAndReduce_dabAcCaCBAcCcaDA() {
        Polymer p = parse("dabAcCaCBAcCcaDA");
        p.reduce();
        assertEquals("dabCBAcaDA", p.toString());
    }

    @Test
    public void parseAndReduce_abAcCaCBAcCcaA() {
        Polymer p = parse("abAcCaCBAcCcaA");
        p.reduce();
        assertEquals("abCBAc", p.toString());
    }

}
package com.barneyb.aoc2018.day05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

abstract public class PolymerTest {

    abstract Polymer parse(String s);

    @Test
    public void length() {
        Polymer p = StringPolymer.parse("aA");
        assertEquals(2, p.length());
        p.reduce(); // to ""
        assertEquals(0, p.length());
    }

    @Test
    public void parseAndReduce_aA() {
        StringPolymer p = StringPolymer.parse("aA");
        p.reduce();
        assertEquals("", p.toString());
    }

    @Test
    public void parseAndReduce_Aa() {
        StringPolymer p = StringPolymer.parse("Aa");
        p.reduce();
        assertEquals("", p.toString());
    }

    @Test
    public void parseAndReduce_abBA() {
        StringPolymer p = StringPolymer.parse("abBA");
        p.reduce();
        assertEquals("", p.toString());
    }

    @Test
    public void parseAndReduce_abAB() {
        StringPolymer p = StringPolymer.parse("abAB");
        p.reduce();
        assertEquals("abAB", p.toString());
    }

    @Test
    public void parseAndReduce_aabAAB() {
        StringPolymer p = StringPolymer.parse("aabAAB");
        p.reduce();
        assertEquals("aabAAB", p.toString());
    }

    @Test
    public void parseAndReduce_dabAcCaCBAcCcaDA() {
        StringPolymer p = StringPolymer.parse("dabAcCaCBAcCcaDA");
        p.reduce();
        assertEquals("dabCBAcaDA", p.toString());
    }

}
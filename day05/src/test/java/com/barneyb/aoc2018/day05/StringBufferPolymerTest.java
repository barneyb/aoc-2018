package com.barneyb.aoc2018.day05;

public class StringBufferPolymerTest extends PolymerTest {

    @Override
    Polymer parse(String s) {
        return StringBufferPolymer.parse(s);
    }

}
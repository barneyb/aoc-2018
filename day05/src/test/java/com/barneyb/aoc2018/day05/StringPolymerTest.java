package com.barneyb.aoc2018.day05;

public class StringPolymerTest extends PolymerTest {

    @Override
    Polymer parse(String s) {
        return StringPolymer.parse(s);
    }

}
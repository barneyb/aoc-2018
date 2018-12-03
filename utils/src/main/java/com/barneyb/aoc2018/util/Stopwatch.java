package com.barneyb.aoc2018.util;

public class Stopwatch {

    private final long start = System.currentTimeMillis();

    public long elapsed() {
        return System.currentTimeMillis() - start;
    }

}

package com.barneyb.aoc2018.util;

public class Stopwatch {

    private long start;

    public Stopwatch() {
        this.start = System.currentTimeMillis();
    }

    public long elapsed() {
        return System.currentTimeMillis() - start;
    }

}

package com.barneyb.aoc2018.util;

public class Stopwatch {

    private final long start = System.currentTimeMillis();
    private long stop = 0;

    public long stop() {
        stop = System.currentTimeMillis();
        return elapsed();
    }

    public long elapsed() {
        return (stop == 0 ? System.currentTimeMillis() : stop) - start;
    }

}

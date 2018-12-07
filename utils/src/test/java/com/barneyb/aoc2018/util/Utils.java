package com.barneyb.aoc2018.util;

public class Utils {

    static int iteratorLength(Iterable iterable) {
        int size = 0;
        for (Object it : iterable) {
            size += 1;
        }
        return size;
    }

}

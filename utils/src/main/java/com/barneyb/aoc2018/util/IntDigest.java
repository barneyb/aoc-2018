package com.barneyb.aoc2018.util;

public class IntDigest {
    private final int[] ns;
    private int i;

    public IntDigest(int[] ns) {
        this(ns, 0);
    }

    public IntDigest(int[] ns, int i) {
        this.ns = ns;
        this.i = i;
    }

    public boolean hasNext() {
        return i < ns.length;
    }

    public int next() {
        return ns[i++];
    }

}

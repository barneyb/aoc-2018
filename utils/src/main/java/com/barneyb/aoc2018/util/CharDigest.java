package com.barneyb.aoc2018.util;

public class CharDigest {

    private final char[] cs;
    private int i;

    public CharDigest(String s) {
        this.cs = s.toCharArray();
    }

    public CharDigest(char[] cs) {
        this(cs, 0);
    }

    public CharDigest(char[] cs, int i) {
        this.cs = cs;
        this.i = i;
    }

    public boolean hasNext() {
        return i < cs.length;
    }

    public char next() {
        return cs[i++];
    }

}

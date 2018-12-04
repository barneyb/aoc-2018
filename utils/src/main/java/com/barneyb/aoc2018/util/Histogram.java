package com.barneyb.aoc2018.util;

public class Histogram<T extends Comparable<T>> extends BST<T, Integer> {

    public static Histogram<Character> fromString(String s) {
        Histogram<Character> h = new Histogram<>();
        Character c;
        for (int i = 0, l = s.length(); i < l; i++) {
            h.count(s.charAt(i));
        }
        return h;
    }

    public void count(T c) {
        add(c, 1);
    }

    public void add(T c, int n) {
        if (contains(c)) {
            put(c, get(c) + n);
        } else {
            put(c, n);
        }
    }

    /**
     * I return zero for keys which have no value, not null.
     */
    @Override
    public Integer get(T key) {
        Integer i = super.get(key);
        return i == null ? 0 : i;
    }

    /**
     * Histograms contain a value for every key, defaulting to zero.
     */
    @Override
    public boolean contains(T key) {
        return true;
    }


}

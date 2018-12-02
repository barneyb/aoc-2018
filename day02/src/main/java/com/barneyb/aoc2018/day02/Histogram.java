package com.barneyb.aoc2018.day02;

import com.barneyb.aoc2018.util.BST;

public class Histogram<T extends Comparable<T>> extends BST<T, Integer> {

    static Histogram<Character> fromString(String s) {
        Histogram<Character> h = new Histogram<>();
        Character c;
        for (int i = 0, l = s.length(); i < l; i++) {
            c = s.charAt(i);
            if (h.contains(c)) {
                h.put(c, h.get(c) + 1);
            } else {
                h.put(c, 1);
            }
        }
        return h;
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

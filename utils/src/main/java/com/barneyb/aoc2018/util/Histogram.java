package com.barneyb.aoc2018.util;

public class Histogram<T extends Comparable<T>> extends BST<T, Integer> {

    public static Histogram<Character> ofCharacters(String s) {
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

    public T leastFrequent() {
        T element = null;
        int frequency = Integer.MAX_VALUE;
        for (T g : keys()) {
            Integer f = get(g);
            if (f < frequency) {
                frequency = f;
                element = g;
            }
        }
        return element;
    }

    public T mostFrequent() {
        T element = null;
        int frequency = -1;
        for (T g : keys()) {
            Integer f = get(g);
            if (f > frequency) {
                frequency = f;
                element = g;
            }
        }
        return element;
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

    public String toBarChart() {
        return toBarChart(70);
    }

    public String toBarChart(int width) {
        StringBuilder sb = new StringBuilder();
        int maxFreq = get(mostFrequent());
        int ll = 0;
        int fl = 0;
        for (T key : keys()) {
            ll = Math.max(ll, key.toString().length());
            fl = Math.max(fl, get(key).toString().length());
        }
        for (T key : keys()) {
            Integer v = get(key);
            int l = (int) (1.0 * v / maxFreq * (width - ll - fl - 4));
            sb.append(String.format("%" + ll + "s (%" + fl + "d) ", key, v));
            for (int i = 0; i < l; i++) {
                sb.append('#');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

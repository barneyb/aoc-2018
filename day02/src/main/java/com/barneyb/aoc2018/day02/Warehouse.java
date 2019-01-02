package com.barneyb.aoc2018.day02;

import com.barneyb.aoc2018.util.Histogram;

class Warehouse {

    static Warehouse parse(String input) {
        return new Warehouse(input.trim().split("\n"));
    }

    private final String[] ids;

    Warehouse(String[] ids) {
        this.ids = ids;
    }

    int checksum() {
        Histogram<Character>[] hs = buildFrequencies();
        int two = countWithFrequency(hs, 2);
        int three = countWithFrequency(hs, 3);
        return two * three;
    }

    private Histogram<Character>[] buildFrequencies() {
        @SuppressWarnings("unchecked")
        Histogram<Character>[] hs = new Histogram[ids.length];
        for (int i = 0, l = ids.length; i < l; i++) {
            hs[i] = Histogram.ofCharacters(ids[i]);
        }
        return hs;
    }

    private int countWithFrequency(Histogram<Character>[] hs, int i) {
        int n = 0;
        for (Histogram<Character> h : hs) {
            if (anyLetterWithFrequency(h, i)) {
                n += 1;
            }
        }
        return n;
    }

    private boolean anyLetterWithFrequency(Histogram<Character> h, int i) {
        for (Character c : h.keys()) {
            if (h.get(c) == i) {
                return true;
            }
        }
        return false;
    }

}

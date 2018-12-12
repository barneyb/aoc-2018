package com.barneyb.aoc2018.day12;

import com.barneyb.aoc2018.util.Bag;

public class Pots {

    private StringBuilder state;
    private int offset;
    private final Bag<String> growthPatterns;

    public Pots(String state, Bag<String> growthPatterns) {
        this.state = new StringBuilder(state);
        this.growthPatterns = growthPatterns;
    }

    public void tick() {
        int i = state.indexOf("#");
        if (i < 0) {
            throw new IllegalStateException("Ain't no more plants, yo!");
        }
        if (i < 4) {
            // need to add periods
            for (int j = i; j < 4; j++) {
                state.insert(0, ".");
                offset -= 1;
            }
        }
        while (state.lastIndexOf("#") > state.length() - 5) {
            state.append('.');
        }
        StringBuilder next = new StringBuilder();
        for (String ptn : growthPatterns) {
            int start = -1;
            while (true) {
                int p = state.indexOf(ptn, start);
                if (p < 0) break;
                while (next.length() < p + 3) {
                    next.append('.');
                }
                next.setCharAt(p + 2, '#');
                start = p + 1;
            }
        }
        i = next.indexOf("#");
        if (i > 0) {
            next.delete(0, i);
            offset += i;
        }
        state = next;
    }

    public String state() {
        return state.toString();
    }

    public int offset() {
        return offset;
    }

    Iterable<Integer> potsWithPlants() {
        Bag<Integer> b = new Bag<>();
        for (int i = 0, l = state.length(); i < l; i++) {
            if (state.charAt(i) == '#') {
                b.add(i + offset);
            }
        }
        return b;
    }

}

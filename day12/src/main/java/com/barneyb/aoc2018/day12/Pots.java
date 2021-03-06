package com.barneyb.aoc2018.day12;

import com.barneyb.aoc2018.util.Bag;

public class Pots {

    private StringBuilder state;
    private int offset;
    private final Bag<String> growthPatterns;
    private boolean stable;

    public Pots(String state, Bag<String> growthPatterns) {
        this.state = new StringBuilder(state.replace('.', ' '));
        this.growthPatterns = new Bag<>();
        for (String p : growthPatterns) {
            this.growthPatterns.add(p.replace('.', ' '));
        }
    }

    public void tick() {
        int i = state.indexOf("#");
        if (i < 0) {
            throw new IllegalStateException("Ain't no more plants, yo!");
        }
        if (i < 4) {
            // need to add periods
            for (int j = i; j < 4; j++) {
                state.insert(0, " ");
                offset -= 1;
            }
        }
        while (state.lastIndexOf("#") > state.length() - 5) {
            state.append(' ');
        }
        StringBuilder next = new StringBuilder(state.length() + 2);
        for (int j = 0, l = state.length() + 2; j < l; j += 10) {
            next.append("          ");
        }
        for (String ptn : growthPatterns) {
            int start = -1;
            while (true) {
                int p = state.indexOf(ptn, start);
                if (p < 0) break;
                next.setCharAt(p + 2, '#');
                start = p + 1;
            }
        }
        i = next.indexOf("#");
        if (i > 0) {
            next.delete(0, i);
            offset += i;
        }
        i = next.lastIndexOf("#");
        if (i < next.length() - 1) {
            next.delete(i + 1, next.length());
        }
        if (state.toString().trim().equals(next.toString().trim())) {
            stable = true;
        }
        state = next;
    }

    public String state() {
        return state.toString();
    }

    public int offset() {
        return offset;
    }

    public boolean stable() {
        return stable;
    }

    public int plantCount() {
        int c = 0;
        for (int i = 0, l = state.length(); i < l; i++) {
            if (state.charAt(i) == '#') {
                c += 1;
            }
        }
        return c;
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

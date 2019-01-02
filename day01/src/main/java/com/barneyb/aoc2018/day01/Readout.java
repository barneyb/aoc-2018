package com.barneyb.aoc2018.day01;

import com.barneyb.aoc2018.api.Set;
import com.barneyb.aoc2018.util.TreeSet;

class Readout {

    static Readout parse(String input) {
        String[] lines = input.trim().split("\n");
        int[] drifts = new int[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            drifts[i] = Integer.parseInt(lines[i]);
        }
        return new Readout(drifts);
    }

    private final int[] drifts;

    Readout(int[] drifts) {
        this.drifts = drifts;
    }

    public int sum() {
        int sum = 0;
        for (int drift : drifts) {
            sum += drift;
        }
        return sum;
    }

    public int firstRepeat() {
        Set<Integer> found = new TreeSet<>();
        int sum = 0;
        found.add(sum);
        while (true) {
            for (int drift : drifts) {
                sum += drift;
                if (found.contains(sum)) {
                    return sum;
                }
                found.add(sum);
            }
        }
    }

}

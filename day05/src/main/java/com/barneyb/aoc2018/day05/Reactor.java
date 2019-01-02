package com.barneyb.aoc2018.day05;

class Reactor {

    public int reducedLength(String polymer) {
        Polymer p = LinkedPolymer.parse(polymer);
        p.reduce();
        return p.length();
    }

    public int reducedLengthWithReplacement(String polymer) {
        int best = Integer.MAX_VALUE;
        for (int i = 'A'; i <= 'Z'; i++) {
            int n = reducedLength(polymer
                    .replace("" + (char) i, "")
                    .replace("" + (char) (i + 32), "")
            );
            if (n < best) {
                best = n;
            }
        }
        return best;
    }

}

package com.barneyb.aoc2018.day16;

import com.barneyb.aoc2018.util.Scanner;

public class Sample {

    static Sample parse(String before, String ins, String after) {
        int[] in = new int[4];
        Scanner s = new Scanner(ins);
        for (int i = 0; i < in.length; i++) {
            in[i] = s.skipWS().readInt();
        }
        return new Sample(
                parseState("Before", before),
                in,
                parseState("After", after));
    }

    private static int[] parseState(String label, String input) {
        int[] b = new int[4];
        Scanner s = new Scanner(input);
        b[0] = s.skip(label).skip(":").skipWS().skip("[").readInt();
        b[1] = s.skip(", ").readInt();
        b[2] = s.skip(", ").readInt();
        b[3] = s.skip(", ").readInt();
        s.skip("]");
        return b;
    }

    private final int[] before;
    private final int[] instruction;
    private final int[] after;

    public Sample(int[] before, int[] instruction, int[] after) {
        this.before = before;
        this.instruction = instruction;
        this.after = after;
    }

    int[] before() {
        return before;
    }

    int[] instruction() {
        return instruction;
    }

    int[] after() {
        return after;
    }

    boolean test(Op op) {
        int[] r = new int[4];
        System.arraycopy(before, 0, r, 0, 4);
        op.execute(r, instruction[1], instruction[2], instruction[3]);
        return r[0] == after[0] &&
                r[1] == after[1] &&
                r[2] == after[2] &&
                r[3] == after[3];
    }

}

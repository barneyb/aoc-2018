package com.barneyb.aoc2018.day16;

import com.barneyb.aoc2018.util.Scanner;

public class Sample {

    static Sample parse(String before, String ins, String after) {
        return new Sample(
                parseState("Before", before),
                Instruction.parse(ins),
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
    private final Instruction instruction;
    private final int[] after;

    public Sample(int[] before, Instruction instruction, int[] after) {
        this.before = before;
        this.instruction = instruction;
        this.after = after;
    }

    int[] before() {
        return before;
    }

    Instruction instruction() {
        return instruction;
    }

    int[] after() {
        return after;
    }

    boolean test(Op op) {
        int[] r = new int[4];
        System.arraycopy(before, 0, r, 0, 4);
        op.execute(r, instruction);
        return r[0] == after[0] &&
                r[1] == after[1] &&
                r[2] == after[2] &&
                r[3] == after[3];
    }

}

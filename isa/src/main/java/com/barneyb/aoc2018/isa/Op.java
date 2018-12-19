package com.barneyb.aoc2018.isa;

public interface Op extends Comparable<Op> {

    Op[] OPS = {
            (int[] rs, Instruction i) -> rs[i.a] + rs[i.b],
            (int[] rs, Instruction i) -> rs[i.a] + i.b,
            (int[] rs, Instruction i) -> rs[i.a] * rs[i.b],
            (int[] rs, Instruction i) -> rs[i.a] * i.b,
            (int[] rs, Instruction i) -> rs[i.a] & rs[i.b],
            (int[] rs, Instruction i) -> rs[i.a] & i.b,
            (int[] rs, Instruction i) -> rs[i.a] | rs[i.b],
            (int[] rs, Instruction i) -> rs[i.a] | i.b,
            (int[] rs, Instruction i) -> rs[i.a],
            (int[] rs, Instruction i) -> i.a,
            (int[] rs, Instruction i) -> i.a > rs[i.b] ? 1 : 0,
            (int[] rs, Instruction i) -> rs[i.a] > i.b ? 1 : 0,
            (int[] rs, Instruction i) -> rs[i.a] > rs[i.b] ? 1 : 0,
            (int[] rs, Instruction i) -> i.a == rs[i.b] ? 1 : 0,
            (int[] rs, Instruction i) -> rs[i.a] == i.b ? 1 : 0,
            (int[] rs, Instruction i) -> rs[i.a] == rs[i.b] ? 1 : 0,
    };

    String[] OP_NAMES = {
            "addr",
            "addi",
            "mulr",
            "muli",
            "banr",
            "bani",
            "borr",
            "bori",
            "setr",
            "seti",
            "gtir",
            "gtri",
            "gtrr",
            "eqir",
            "eqri",
            "eqrr",
    };

    int result(int[] rs, Instruction i);

    default void execute(int[] rs, Instruction i) {
        rs[i.c] = result(rs, i);
    }

    default int compareTo(Op o) {
        return getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
    }

}

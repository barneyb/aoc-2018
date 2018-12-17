package com.barneyb.aoc2018.day16;

public interface Op extends Comparable<Op> {

    int result(int[] rs, Instruction i);

    default void execute(int[] rs, Instruction i) {
        rs[i.c] = result(rs, i);
    }

    default int compareTo(Op o) {
        return getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
    }

}

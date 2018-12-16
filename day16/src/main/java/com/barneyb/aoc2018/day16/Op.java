package com.barneyb.aoc2018.day16;

public interface Op extends Comparable<Op> {

    void execute(int[] registers, int a, int b, int c);

    default void execute(int[] rs, Instruction i) {
        execute(rs, i.a, i.b, i.c);
    }

    default int compareTo(Op o) {
        return getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
    }

}

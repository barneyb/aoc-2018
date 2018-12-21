package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.Op;

abstract class BaseOp implements Op {

    abstract int result(int[] rs, Instruction i);

    final public void execute(int[] rs, Instruction i) {
        rs[i.c()] = result(rs, i);
    }

    public final String name() {
        return getClass().getSimpleName();
    }

    public final int compareTo(Op o) {
        return name().compareTo(o.name());
    }

    @Override
    public final String toString() {
        return name();
    }

}

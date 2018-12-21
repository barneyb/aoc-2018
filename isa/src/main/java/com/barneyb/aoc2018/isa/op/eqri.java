package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;

public class eqri extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] == i.b() ? 1 : 0;
    }
}

package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;

public class eqir extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return i.a() == rs[i.b()] ? 1 : 0;
    }
}

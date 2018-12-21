package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassemble;

public class mulr extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] * rs[i.b()];
    }

    @Override
    public String disassemble(int ipr, int a, int b) {
        return Disassemble.registerName(ipr, a) + " * " + Disassemble.registerName(ipr, b);
    }
}

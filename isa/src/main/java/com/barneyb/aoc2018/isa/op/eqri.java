package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassemble;

public class eqri extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] == i.b() ? 1 : 0;
    }

    @Override
    public String disassemble(int ipr, int a, int b) {
        return Disassemble.registerName(ipr, a) + " == " + b + " ? 1 : 0";
    }
}

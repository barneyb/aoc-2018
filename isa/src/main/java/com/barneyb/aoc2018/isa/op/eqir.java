package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassemble;

public class eqir extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return i.a() == rs[i.b()] ? 1 : 0;
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        return String.format(
                "%2s  = %d == %s ? 1 : 0",
                Disassemble.registerName(ipr, c),
                a,
                Disassemble.registerName(ipr, b));
    }
}

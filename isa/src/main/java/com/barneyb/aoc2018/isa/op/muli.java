package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassembly;

public class muli extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] * i.b();
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        if (a == c) return String.format(
                "%2s *= %d",
                Disassembly.registerName(ipr, c),
                b);
        return String.format(
                "%2s  = %s * %d",
                Disassembly.registerName(ipr, c),
                Disassembly.registerName(ipr, a),
                b);
    }
}

package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassembly;

public class eqrr extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] == rs[i.b()] ? 1 : 0;
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        return String.format(
                "%2s  = %s == %s ? 1 : 0",
                Disassembly.registerName(ipr, c),
                Disassembly.registerName(ipr, a),
                Disassembly.registerName(ipr, b));
    }
}

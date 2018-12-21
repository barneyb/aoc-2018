package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassemble;

public class gtri extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] > i.b() ? 1 : 0;
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        return String.format(
                "%2s  = %s > %d ? 1 : 0",
                Disassemble.registerName(ipr, c),
                Disassemble.registerName(ipr, a),
                b);
    }
}

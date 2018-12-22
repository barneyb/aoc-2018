package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassembly;

public class banr extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] & rs[i.b()];
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        if (a == c) return String.format(
                "%2s &= %s",
                Disassembly.registerName(ipr, c),
                Disassembly.registerName(ipr, b));
        return String.format(
                "%2s  = %s & %s",
                Disassembly.registerName(ipr, c),
                Disassembly.registerName(ipr, a),
                Disassembly.registerName(ipr, b));
    }
}

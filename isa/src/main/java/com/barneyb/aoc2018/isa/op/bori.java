package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassemble;

public class bori extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] | i.b();
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        if (a == c) return String.format(
                "%2s |= %d",
                Disassemble.registerName(ipr, c),
                b);
        return String.format(
                "%2s  = %s | %d",
                Disassemble.registerName(ipr, c),
                Disassemble.registerName(ipr, a),
                b);
    }
}

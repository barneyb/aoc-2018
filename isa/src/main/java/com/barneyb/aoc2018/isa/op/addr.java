package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassemble;

public class addr extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return rs[i.a()] + rs[i.b()];
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        if (a == c) return String.format(
                "%2s += %s",
                Disassemble.registerName(ipr, c),
                Disassemble.registerName(ipr, b));
        return String.format(
                "%2s  = %s + %s",
                Disassemble.registerName(ipr, c),
                Disassemble.registerName(ipr, a),
                Disassemble.registerName(ipr, b));
    }
}

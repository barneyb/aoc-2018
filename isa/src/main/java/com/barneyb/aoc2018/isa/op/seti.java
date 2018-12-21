package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.util.Disassemble;

public class seti extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return i.a();
    }

    @Override
    public String disassemble(int ipr, int a, int b, int c) {
        return String.format(
                "%2s  = %d",
                Disassemble.registerName(ipr, c),
                a);
    }
}
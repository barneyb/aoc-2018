package com.barneyb.aoc2018.isa.op;

import com.barneyb.aoc2018.isa.Instruction;

public class seti extends BaseOp {
    @Override
    public int result(int[] rs, Instruction i) {
        return i.a();
    }

    @Override
    public String disassemble(int ipr, int a, int b) {
        return "" + a;
    }
}

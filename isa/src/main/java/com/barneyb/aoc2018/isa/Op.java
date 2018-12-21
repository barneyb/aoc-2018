package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.isa.op.*;

public interface Op extends Comparable<Op> {

    Op[] OPS = {
            new addr(),
            new addi(),
            new mulr(),
            new muli(),
            new banr(),
            new bani(),
            new borr(),
            new bori(),
            new setr(),
            new seti(),
            new gtir(),
            new gtri(),
            new gtrr(),
            new eqir(),
            new eqri(),
            new eqrr(),
    };

    String name();

    void execute(int[] rs, Instruction i);

    String disassemble(int ipr, int a, int b, int c);

}

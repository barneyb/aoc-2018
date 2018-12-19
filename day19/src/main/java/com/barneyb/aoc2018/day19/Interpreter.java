package com.barneyb.aoc2018.day19;

import com.barneyb.aoc2018.isa.Instruction;

import static com.barneyb.aoc2018.isa.Op.OPS;

class Interpreter {

    private final Program p;
    private int[] registers = new int[6];

    Interpreter(Program p) {
        this.p = p;
    }

    void run() {
        for (int ip = 0; ip >= 0 && ip < p.instructions.length; ip++) {
            registers[p.ipr] = ip;
            Instruction i = p.instructions[ip];
            OPS[i.opcode].execute(registers, i);
            ip = registers[p.ipr];
        }
    }

    int registerZero() {
        return register(0);
    }

    int register(int n) {
        return registers[n];
    }

}

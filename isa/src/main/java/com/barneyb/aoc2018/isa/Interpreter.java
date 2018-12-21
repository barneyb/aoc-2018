package com.barneyb.aoc2018.isa;

import java.util.function.Predicate;

import static com.barneyb.aoc2018.isa.Op.OPS;
import static com.barneyb.aoc2018.isa.Op.OP_NAMES;

public class Interpreter {

    private final Program p;
    private final int[] registers = new int[6];

    public Interpreter(Program p) {
        this.p = p;
    }

    public void run() {
        run(i -> false);
    }

    private void run(Predicate<Instruction> until) {
        for (int ip = 0; ip >= 0 && ip < p.instructions.length; ip++) {
            registers[p.ipr] = ip;
            Instruction i = p.instructions[ip];
            if (until.test(i)) break;
            OPS[i.opcode].execute(registers, i);
            ip = registers[p.ipr];
        }
    }

    public void runToFirstGt() {
        run(i -> OP_NAMES[i.opcode].startsWith("gt"));
    }

    public int registerCount() {
        return registers.length;
    }

    public int register(int n) {
        return registers[n];
    }

    public void register(int n, int v) {
        registers[n] = v;
    }

}

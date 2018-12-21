package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.TreeSet;

import java.util.function.BiPredicate;

import static com.barneyb.aoc2018.isa.Op.OPS;
import static com.barneyb.aoc2018.isa.Op.OP_NAMES;

public class Interpreter {

    private final Program p;
    private final int[] registers = new int[6];

    public Interpreter(Program p) {
        this.p = p;
    }

    public void run() {
        run((ins, ip) -> false);
    }

    TreeSet<Integer> es = new TreeSet<>();
    public int prev = -1;
    private void run(BiPredicate<Instruction, Integer> until) {
        for (int ip = 0; ip >= 0 && ip < p.instructions.length; ip++) {
            registers[p.ipr] = ip;
            Instruction i = p.instructions[ip];
            if (ip == 28) {
                if (es.contains(registers[4])) {
                    break;
                }
                es.add(prev = registers[4]);
            }
            if (until.test(i, ip)) break;
            OPS[i.opcode].execute(registers, i);
            ip = registers[p.ipr];
        }
    }

    public void runToFirstGt() {
        run((ins, ip) -> OP_NAMES[ins.opcode].startsWith("gt"));
    }
    public void runToInstruction(int idx) {
        run((ins, ip) -> ip == idx);
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

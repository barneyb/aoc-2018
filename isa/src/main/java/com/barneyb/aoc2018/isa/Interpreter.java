package com.barneyb.aoc2018.isa;

public class Interpreter {

    protected final Program p;
    private final int[] registers = new int[6];

    public Interpreter(Program p) {
        this.p = p;
    }

    public void run() {
        Instruction ins;
        for (int ip = 0; ip >= 0 && ip < p.instructions.length; ip++) {
            registers[p.ipr] = ip;
            ins = p.instructions[ip];
            if (terminate(ip, ins)) break;
            ins.execute(registers);
            ip = registers[p.ipr];
        }
    }

    // I am checked every tick; if I return true, execution will halt
    protected boolean terminate(int ip, Instruction instruction) {
        return false;
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

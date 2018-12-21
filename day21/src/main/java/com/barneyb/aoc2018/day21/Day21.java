package com.barneyb.aoc2018.day21;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.Interpreter;
import com.barneyb.aoc2018.isa.Program;
import com.barneyb.aoc2018.util.*;

public class Day21 extends OneShotDay {

    private static class CycleFindingInterpreter extends Interpreter {

        private final int targetIdx;
        private final int targetRegister;
        private final TreeSet<Integer> uniquer = new TreeSet<>();
        private int lastInCycle = -1;

        public CycleFindingInterpreter(Program p, int targetIdx, int targetRegister) {
            super(p);
            this.targetIdx = targetIdx;
            this.targetRegister = targetRegister;
        }

        @Override
        protected boolean terminate(int ip, Instruction instruction) {
            if (ip != targetIdx) return false;
            int v = register(targetRegister);
            if (uniquer.contains(v)) return true;
            uniquer.add(v);
            lastInCycle = v;
            return false;
        }
    }

    @Override
    public Answers solve(String input) {
        System.out.println("Day 21 takes 25-30 seconds to solve...");
        Program p = Program.parse(input);

        // part one
        // find the instruction that compares register 0 to something
        // and identify the register compared against register 0
        int targetIdx = findTargetInstructionIndex(p);
        Instruction targetIns = p.instruction(targetIdx);
        int targetRegister = targetIns.a() == 0 ? targetIns.b() : targetIns.a();
        // run to the target instruction
        Interpreter interpOne = new Interpreter(p) {
            @Override
            protected boolean terminate(int ip, Instruction instruction) {
                return ip == targetIdx;
            }
        };
        interpOne.run();
        // get the value of the target register
        int one = interpOne.register(targetRegister);

        // part two
        // monitor target register at target instruction
        // find a cycle of values
        CycleFindingInterpreter interpTwo = new CycleFindingInterpreter(p, targetIdx, targetRegister);
        interpTwo.run();
        // get the last value in the cycle (one before the first repeat)
        int two = interpTwo.lastInCycle;
        return new Answers(one, two);
    }

    private int findTargetInstructionIndex(Program p) {
        Instruction[] instructions = p.instructions();
        for (int i = 0; i < instructions.length; i++) {
            Instruction ins = instructions[i];
            if (!"eqrr".equals(ins.opName())) continue;
            if (ins.a() == 0 || ins.b() == 0) return i;
        }
        throw new RuntimeException("No target instruction index found. :(");
    }

    public static void main(String[] args)  {
        Day21 d = new Day21();
        String input = FileUtils.readFile("day21/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("Expect [%d, %d]%n", 15690445, 936387);
        System.out.printf("%s in %d ms%n", a, e);
    }

}

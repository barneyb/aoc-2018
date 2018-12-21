package com.barneyb.aoc2018.day19;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.Interpreter;
import com.barneyb.aoc2018.isa.Program;
import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Stopwatch;

public class Day19 extends OneShotDay {

    private static class ToFirstGTInterpreter extends Interpreter {

        public ToFirstGTInterpreter(Program p) {
            super(p);
        }

        @Override
        protected boolean terminate(int ip, Instruction instruction) {
            return instruction.opName().startsWith("gt");
        }

    }

    @Override
    public Answers solve(String input) {
        Program p = Program.parse(input);
        Interpreter interpreter = new Interpreter(p);
        interpreter.run();
        int one = interpreter.register(0);
        System.out.println("Part One: " + one);

        // find the register we care about
        interpreter = new ToFirstGTInterpreter(p);
        interpreter.run();
        int target = -1;
        for (int i = 0, l = interpreter.registerCount(); i < l; i++) {
            if (sumOfFactorPairs(interpreter.register(i)) == one) {
                target = i;
            }
        }
        if (target < 0) return new Answers(one);

        // initialize with the full run
        interpreter = new ToFirstGTInterpreter(p);
        interpreter.register(0, 1);
        interpreter.run();

        // get the answer. efficiently.
        int two = sumOfFactorPairs(interpreter.register(target));

        return new Answers(one, two);
    }

    static int sumOfFactorPairs(int n) {
        int sum = 0;
        for (int f = 1, l = (int) Math.sqrt(n); f <= l; f++) {
            if (n % f == 0) {
                sum += f;
                sum += n / f;
            }
        }
        return sum;
    }

    public static void main(String[] args)  {
        Day19 d = new Day19();
        String input = FileUtils.readFile("day19/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

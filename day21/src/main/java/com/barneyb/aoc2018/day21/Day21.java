package com.barneyb.aoc2018.day21;

import com.barneyb.aoc2018.isa.Interpreter;
import com.barneyb.aoc2018.isa.Program;
import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Stopwatch;

public class Day21 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Program p = Program.parse(input);
        Interpreter interpreter = new Interpreter(p);
//        interpreter.runToInstruction(28);
        int one = 4;//interpreter.register(4);
//        System.out.println(one);
        interpreter = new Interpreter(p);
        interpreter.run();
        return new Answers(
                one
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day21 d = new Day21();
        String input = FileUtils.readFile("day21/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

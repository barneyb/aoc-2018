package com.barneyb.aoc2018.day19;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Stopwatch;

public class Day19 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Program p = Program.parse(input);
        Interpreter i = new Interpreter(p);
        i.run();
        return new Answers(
                i.registerZero()
//                , input.trim().length()
        );
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

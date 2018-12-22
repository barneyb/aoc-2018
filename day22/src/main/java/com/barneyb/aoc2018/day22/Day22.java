package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Stopwatch;

public class Day22 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        System.out.println("Day 22 takes 25-30 seconds to solve...");
        Map m = Map.parse(input);
        return new Answers(
                m.riskLevel(),
                new Explorer(m).fastest()
        );
    }

    public static void main(String[] args)  {
        Day22 d = new Day22();
        String input = FileUtils.readFile("day22/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

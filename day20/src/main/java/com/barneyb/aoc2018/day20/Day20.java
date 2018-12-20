package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.*;

public class Day20 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length()
                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day20 d = new Day20();
        String input = FileUtils.readFile("day20/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

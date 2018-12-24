package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.*;

public class Day24 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length()
                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day24 d = new Day24();
        String input = FileUtils.readFile("day24/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

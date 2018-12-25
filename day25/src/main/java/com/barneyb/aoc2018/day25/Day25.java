package com.barneyb.aoc2018.day25;

import com.barneyb.aoc2018.util.*;

public class Day25 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length()
                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day25 d = new Day25();
        String input = FileUtils.readFile("day25/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

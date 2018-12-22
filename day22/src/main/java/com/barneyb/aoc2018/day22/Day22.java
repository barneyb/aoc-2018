package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.*;

public class Day22 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length()
                , input.trim().length()
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

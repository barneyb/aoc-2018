package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day06 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Plotter p = Plotter.parse(input);
        return new Answers(
                p.largestArea(),
                p.regionWithin(10000)
        );
    }

    public static void main(String[] args)  {
        Day06 d = new Day06();
        String input = FileUtils.readFile("day06/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

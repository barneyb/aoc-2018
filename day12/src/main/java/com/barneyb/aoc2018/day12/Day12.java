package com.barneyb.aoc2018.day12;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

public class Day12 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day12 d = new Day12();
        String input = FileUtils.readFile("day12/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

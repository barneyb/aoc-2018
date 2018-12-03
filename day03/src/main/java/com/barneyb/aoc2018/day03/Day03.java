package com.barneyb.aoc2018.day03;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day03 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day03 d = new Day03();
        String input = FileUtils.readFile("day03/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

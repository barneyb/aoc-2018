package com.barneyb.aoc2018.day05;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

public class Day05 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day05 d = new Day05();
        String input = FileUtils.readFile("day05/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

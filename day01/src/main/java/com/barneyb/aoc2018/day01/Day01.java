package com.barneyb.aoc2018.day01;

import com.barneyb.aoc2018.api.impl.Answers;
import com.barneyb.aoc2018.api.impl.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

public class Day01 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day01 d = new Day01();
        String input = FileUtils.readFile("day01/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

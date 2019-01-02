package com.barneyb.aoc2018.day00;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day00 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day00 d = new Day00();
        String input = FileUtils.readFile("day00/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

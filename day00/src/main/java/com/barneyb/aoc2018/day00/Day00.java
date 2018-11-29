package com.barneyb.aoc2018.day00;

import com.barneyb.aoc2018.api.impl.Answers;
import com.barneyb.aoc2018.api.impl.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

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
        String input = FileUtils.readFile("input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

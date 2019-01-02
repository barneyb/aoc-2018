package com.barneyb.aoc2018.day01;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day01 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Readout r = Readout.parse(input);
        return new Answers(
                r.sum(),
                r.firstRepeat()
        );
    }

    public static void main(String[] args)  {
        Day01 d = new Day01();
        String input = FileUtils.readFile("day01/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

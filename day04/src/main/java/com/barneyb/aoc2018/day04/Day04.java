package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day04 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Log l = Log.parse(input);
        return new Answers(
                l.sleepiestGuardMinute(),
                l.sleepiestMinuteGuard()
        );
    }

    public static void main(String[] args)  {
        Day04 d = new Day04();
        String input = FileUtils.readFile("day04/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

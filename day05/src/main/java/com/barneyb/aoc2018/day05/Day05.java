package com.barneyb.aoc2018.day05;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day05 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Polymer p = StringPolymer.parse(input.trim());
        return new Answers(
                partOne(p)
        );
    }

    static int partOne(Polymer p) {
        p.reduce();
        return p.length();
    }

    public static void main(String[] args)  {
        Day05 d = new Day05();
        String input = FileUtils.readFile("day05/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

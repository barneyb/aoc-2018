package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day04 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    public static Record[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Record[] rs = new Record[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            rs[i] = Record.fromString(lines[i]);
        }
        return rs;
    }

    public static void main(String[] args)  {
        Day04 d = new Day04();
        String input = FileUtils.readFile("day04/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

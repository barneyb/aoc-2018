package com.barneyb.aoc2018.day05;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day05 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String trimmed = input.trim();
        return new Answers(
                partOne(trimmed),
                partTwo(trimmed)
        );
    }

    static int partOne(String input) {
        Polymer p = StringPolymer.parse(input);
        p.reduce();
        return p.length();
    }

    static int partTwo(String input) {
        int best = Integer.MAX_VALUE;
        for (int i = 'A'; i <= 'Z'; i++) {
            int n = partOne(input
                    .replace("" + (char) i, "")
                    .replace("" + (char) (i + 32), "")
            );
            if (n < best) {
                best = n;
            }
        }
        return best;
    }

    public static void main(String[] args)  {
        Day05 d = new Day05();
        String input = FileUtils.readFile("day05/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

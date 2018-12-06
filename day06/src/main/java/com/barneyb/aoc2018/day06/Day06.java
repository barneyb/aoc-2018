package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day06 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Point[] points = parse(input);
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    static Point[] parse(String input) {
        String[] ls = input.trim().split("\n");
        Point[] ps = new Point[ls.length];
        for (int i = 0, l = ls.length; i < l; i++) {
            String[] parts = ls[i].trim().split(",\\s+");
            ps[i] = new Point(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])
            );
        }
        return ps;
    }

    public static void main(String[] args)  {
        Day06 d = new Day06();
        String input = FileUtils.readFile("day06/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

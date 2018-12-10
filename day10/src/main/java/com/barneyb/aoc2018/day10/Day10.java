package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day10 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Particle[] ps = parse(input);
        return new Answers(
                partOne(ps)
        );
    }

    static Particle[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Particle[] ps = new Particle[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            ps[i] = Particle.parse(lines[i]);
        }
        return ps;
    }

    static String partOne(Particle[] ps) {
        return new Array(ps).message();
    }

    public static void main(String[] args)  {
        Day10 d = new Day10();
        String input = FileUtils.readFile("day10/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

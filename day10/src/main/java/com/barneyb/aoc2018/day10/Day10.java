package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day10 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Particle[] ps = parse(input);
        Array a = new Array(ps);
        long priorArea = a.bounds().area();
        while (true) {
            a.tick();
            long area = a.bounds().area();
            if (area > priorArea) {
                // getting bigger
                a.untick();
                break;
            }
            priorArea = area;
        }
        return new Answers(
                a.toString(),
                a.tickCount()
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

    public static void main(String[] args)  {
        Day10 d = new Day10();
        String input = FileUtils.readFile("day10/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

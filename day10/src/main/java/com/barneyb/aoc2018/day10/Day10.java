package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day10 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Light[] lights = parse(input);
        Pattern p = new Pattern(lights);
        long priorArea = p.bounds().area();
        while (true) {
            p.tick(1);
            long area = p.bounds().area();
            if (area > priorArea) {
                // getting bigger
                p.tick(-1);
                break;
            }
            priorArea = area;
        }
        return new Answers(
                p.toString(),
                p.tickCount()
        );
    }

    static Light[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Light[] lights = new Light[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            lights[i] = Light.parse(lines[i]);
        }
        return lights;
    }

    public static void main(String[] args)  {
        Day10 d = new Day10();
        String input = FileUtils.readFile("day10/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day17 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Vein[] veins = parse(input);
        Earth e = new Earth(veins);
        e.runWater();
        System.out.println(e.toString(true));
        return new Answers(
                e.wetTiles()
                ,e.resTiles()
        );
    }

    static Vein[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Vein[] veins = new Vein[lines.length];
        for (int i = 0; i < lines.length; i++) {
            veins[i] = Vein.parse(lines[i]);
        }
        return veins;
    }

    // 163925 is too high
    // 31848 is too low
    // 31851 is too low
    // 31864 is wrong




    // todo: autocorrected by 3 for the shit at x=416 y=1464..1466


    public static void main(String[] args)  {
        Day17 d = new Day17();
        String input = FileUtils.readFile("day17/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

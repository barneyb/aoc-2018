package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day17 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String[] lines = input.trim().split("\n");
        Vein[] veins = new Vein[lines.length];
        for (int i = 0; i < lines.length; i++) {
            veins[i] = Vein.parse(lines[i]);
        }
        Scan s = new Scan(veins);
        System.out.printf("%s%n", s);
        return new Answers(
                veins.length
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day17 d = new Day17();
        String input = FileUtils.readFile("day17/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

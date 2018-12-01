package com.barneyb.aoc2018.day01;

import com.barneyb.aoc2018.api.impl.Answers;
import com.barneyb.aoc2018.api.impl.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

public class Day01 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        int[] drifts = parse(input);
        int sum = 0;
        for (int drift : drifts) {
            sum += drift;
        }
        return new Answers(
                sum
        );
    }

    private int[] parse(String input) {
        String[] lines = input.trim().split("\n");
        int[] drifts = new int[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            drifts[i] = Integer.parseInt(lines[i]);
        }
        return drifts;
    }

    public static void main(String[] args)  {
        Day01 d = new Day01();
        String input = FileUtils.readFile("day01/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

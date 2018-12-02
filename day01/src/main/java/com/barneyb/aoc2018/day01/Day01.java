package com.barneyb.aoc2018.day01;

import com.barneyb.aoc2018.api.impl.Answers;
import com.barneyb.aoc2018.api.impl.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

import java.util.HashSet;
import java.util.Set;

public class Day01 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        int[] drifts = parse(input);
        return new Answers(
                sum(drifts),
                firstRepeat(drifts)
        );
    }

    private int sum(int[] drifts) {
        int sum = 0;
        for (int drift : drifts) {
            sum += drift;
        }
        return sum;
    }

    int firstRepeat(int[] drifts) {
        Set<Integer> found = new HashSet<>();
        int sum = 0;
        found.add(sum);
        while (true) {
            for (int drift : drifts) {
                sum += drift;
                if (found.contains(sum)) {
                    return sum;
                }
                found.add(sum);
            }
        }
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

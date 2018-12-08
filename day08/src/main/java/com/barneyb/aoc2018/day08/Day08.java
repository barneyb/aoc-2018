package com.barneyb.aoc2018.day08;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day08 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String[] ss = input.trim().split(" ");
        int[] ns = new int[ss.length];
        for (int i = 0, l = ss.length; i < l; i++) {
            ns[i] = Integer.parseInt(ss[i]);
        }
        Tree tree = new Tree(ns);
        return new Answers(
                tree.getMetaSum(),
                tree.getValue()
        );
    }

    public static void main(String[] args)  {
        Day08 d = new Day08();
        String input = FileUtils.readFile("day08/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

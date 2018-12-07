package com.barneyb.aoc2018.day07;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day07 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Prerequisite[] prereqs = parse(input);
        return new Answers(
                partOne(prereqs)
        );
    }

    private String partOne(Prerequisite[] prereqs) {
        return "glerg";
    }

    static Prerequisite[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Prerequisite[] prereqs = new Prerequisite[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            String[] parts = lines[i].split(" ");
            prereqs[i] = new Prerequisite(
                    parts[7],
                    parts[1]
            );
        }
        return prereqs;
    }

    public static void main(String[] args)  {
        Day07 d = new Day07();
        String input = FileUtils.readFile("day07/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

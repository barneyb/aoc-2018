package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day18 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Map m = Map.parse(input);
        m.tick(10);
        return new Answers(
                m.resourceValue()
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day18 d = new Day18();
        String input = FileUtils.readFile("day18/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

package com.barneyb.aoc2018.day14;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day14 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        int goal = Integer.parseInt(input.trim());
        Sim s = new Sim();
        while (s.recipeCount() < goal + 11) {
            s.tick();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s.scoreAt(goal + i));
        }
        return new Answers(
                sb
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day14 d = new Day14();
        String input = FileUtils.readFile("day14/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

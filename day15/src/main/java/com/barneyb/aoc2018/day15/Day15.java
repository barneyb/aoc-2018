package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day15 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Map m = Map.parse(input);
        Engine s = new Engine(m);
        s.run();
        int hp = 0;
        for (Unit u : m.livingUnits()) {
            hp += u.hitPoints();
        }
        return new Answers(
                s.rounds() * hp
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day15 d = new Day15();
        String input = FileUtils.readFile("day15/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Point;

public class Day13 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Map m = Map.parse(input);
        while (m.crashCount() == 0) {
            m.tick();
        }
        Point p = m.locationOfFirstCrash();
        return new Answers(
                p.x + "," + p.y
        );
    }

    public static void main(String[] args)  {
        Day13 d = new Day13();
        String input = FileUtils.readFile("day13/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

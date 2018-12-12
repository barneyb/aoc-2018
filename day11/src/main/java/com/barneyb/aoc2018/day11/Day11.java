package com.barneyb.aoc2018.day11;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Point;

public class Day11 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        int serial = Integer.parseInt(input.trim());
        Grid g = new Grid(serial);
        Point p = g.mostPowerfulPoint().p;
        return new Answers(
            p.x + "," + p.y,
                g.mostPowerfulPoint(5, 27)
        );
    }

    public static void main(String[] args)  {
        Day11 d = new Day11();
        String input = FileUtils.readFile("day11/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

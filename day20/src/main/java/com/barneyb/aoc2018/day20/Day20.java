package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Stopwatch;

public class Day20 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Map m = Map.parse(input);
        return new Answers(
                partOne(m),
                partTwo(m)
        );
    }

    static int partOne(String input) {
        Map m = Map.parse(input);
        return partOne(m);
    }

    private static int partOne(Map m) {
        return m.distanceTo(m.farthestPoint());
    }

    private int partTwo(Map m) {
        return m.roomCount(1000);
    }

    public static void main(String[] args)  {
        Day20 d = new Day20();
        String input = FileUtils.readFile("day20/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

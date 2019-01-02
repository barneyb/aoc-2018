package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.*;

public class Day23 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        System.out.println("Day 23 takes 45-50 seconds to solve...");
        Swarm swarm = Swarm.parse(input);
        return new Answers(
                swarm.inRangeOfStrongest()
                , swarm.distanceToIdealPoint()
        );
    }

    public static void main(String[] args)  {
        Day23 d = new Day23();
        String input = FileUtils.readFile("day23/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %,d ms%n", a, e);
    }

}

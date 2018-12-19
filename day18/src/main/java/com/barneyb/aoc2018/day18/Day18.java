package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.*;

public class Day18 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Map m = Map.parse(input);
        m.tick(10);
        return new Answers(
                m.resourceValue(),
                partTwo(m, 1000000000)
        );
    }

    static int partTwo(Map m, int minute) {
        for (int i = 0; i < 3000; i++) {
            m.tick();
        }
        Histogram<String> h = new Histogram<>();
        for (int i = 0; i < 1000; i++) {
            m.tick();
            h.count(m.toString());
        }
        String sentinel = h.mostFrequent();
        while (! m.toString().equals(sentinel)) {
            m.tick();
        }
        List<Integer> cycle = new List<>();
        do {
            cycle.add(m.resourceValue());
            m.tick();
        } while (! m.toString().equals(sentinel));
        return cycle.get((minute - m.tickCount()) % cycle.size());
    }

    public static void main(String[] args)  {
        Day18 d = new Day18();
        String input = FileUtils.readFile("day18/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

package com.barneyb.aoc2018.day25;

import com.barneyb.aoc2018.util.*;

public class Day25 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String[] lines = input.trim().split("\n");
        Vector[] points = new Vector[lines.length];
        for (int i = 0; i < lines.length; i++) {
            points[i] = Vector.parse(lines[i]);
        }
        UF uf = new UF(points.length);
        for (int i = 0; i < points.length; i++) {
            Vector p = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                Vector q = points[j];
                if (p.md(q) <= 3) {
                    uf.union(i, j);
                }
            }
        }
        return new Answers(
                uf.components()
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day25 d = new Day25();
        String input = FileUtils.readFile("day25/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

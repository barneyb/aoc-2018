package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.*;

public class Day06 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Point[] ps = parse(input);
        constrain(ps);
        return new Answers(
                partOne(ps),
                partTwo(ps, 10000)
        );
    }

    static int partTwo(Point[] ps, int distCap) {
        AllWithinPlot plot = new AllWithinPlot(ps, distCap);
        return plot.getSize();
    }

    private int partOne(Point[] ps) {
        ClosestPlot plot = new ClosestPlot(ps);
        TreeSet<Integer> bas = plot.getBoundaryAreas();
        BST<Integer, Integer> ss = plot.getSizes();
        // todo: BST needs to grow delete.
        int max = 0;
        for (Integer i : ss.keys()) {
            if (bas.contains(i)) {
                continue; // infinite!
            }
            int n = ss.get(i);
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    static Point[] parse(String input) {
        String[] ls = input.trim().split("\n");
        Point[] ps = new Point[ls.length];
        for (int i = 0, l = ls.length; i < l; i++) {
            String[] parts = ls[i].trim().split(",\\s+");
            ps[i] = new Point(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])
            );
        }
        return ps;
    }

    static void constrain(Point[] ps) {
        Point min = min(ps);
        for (int i = 0, l = ps.length; i < l; i++) {
            ps[i] = ps[i].minus(min);
        }
    }

    static Point min(Point[] ps) {
        int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE;
        for (Point p : ps) {
            x = Math.min(x, p.x);
            y = Math.min(y, p.y);
        }
        return new Point(x, y);
    }

    static Point max(Point[] ps) {
        int x = 0, y = 0;
        for (Point p : ps) {
            x = Math.max(x, p.x);
            y = Math.max(y, p.y);
        }
        return new Point(x, y);
    }

    public static void main(String[] args)  {
        Day06 d = new Day06();
        String input = FileUtils.readFile("day06/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

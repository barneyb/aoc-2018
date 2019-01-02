package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.TreeSet;

class Plotter {

    static Plotter parse(String input) {
        String[] ls = input.trim().split("\n");
        Point[] ps = new Point[ls.length];
        for (int i = 0; i < ls.length; i++) {
            ps[i] = Point.parse(ls[i]);
        }
        return new Plotter(ps);
    }

    private final Point[] points;

    Plotter(Point[] points) {
        Point min = min(points);
        for (int i = 0, l = points.length; i < l; i++) {
            points[i] = points[i].minus(min);
        }
        this.points = points;
    }

    Point[] points() {
        return points;
    }

    public int largestArea() {
        ClosestPlot plot = new ClosestPlot(this);
        TreeSet<Integer> bas = plot.getBoundaryAreas();
        BST<Integer, Integer> ss = plot.getSizes();
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

    public int regionWithin(int distCap) {
        AllWithinPlot plot = new AllWithinPlot(this, distCap);
        return plot.getSize();
    }

    private Point min(Point[] ps) {
        int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE;
        for (Point p : ps) {
            x = Math.min(x, p.x);
            y = Math.min(y, p.y);
        }
        return new Point(x, y);
    }

    Point max() {
        int x = 0, y = 0;
        for (Point p : points) {
            x = Math.max(x, p.x);
            y = Math.max(y, p.y);
        }
        return new Point(x, y);
    }

}

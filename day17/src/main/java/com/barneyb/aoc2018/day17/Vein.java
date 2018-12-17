package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.Bounds;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Scanner;
import com.barneyb.aoc2018.util.TreeSet;

public class Vein {

    static Vein parse(String input) {
        Scanner s = new Scanner(input);
        TreeSet<Point> points = new TreeSet<>();
        if (s.probe('x')) {
            // vertical
            int x = s.skip("x=").readInt();
            int y1 = s.skip(",").skipWS().skip("y=").readInt();
            int y2 = s.skip("..").readInt();
            for (int y = y1; y <= y2; y++) {
                points.add(new Point(x, y));
            }
        } else {
            // horizontal
            int y = s.skip("y=").readInt();
            int x1 = s.skip(",").skipWS().skip("x=").readInt();
            int x2 = s.skip("..").readInt();
            for (int x = x1; x <= x2; x++) {
                points.add(new Point(x, y));
            }
        }
        return new Vein(points);
    }

    private final TreeSet<Point> points;
    private final Bounds bounds;

    Vein(TreeSet<Point> points) {
        this.points = points;
        this.bounds = new Bounds(points.min(), points.max());
    }

    Iterable<Point> points() {
        return points;
    }

    Bounds bounds() {
        return bounds;
    }

}

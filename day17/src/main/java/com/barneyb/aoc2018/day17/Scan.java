package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Bounds;
import com.barneyb.aoc2018.util.Point;

public class Scan {

    public static final Point SPRING = new Point(500, 0);

    static final char CLAY = '#';

    private final BST<Point, Character> scan;
    private final Bounds bounds;

    public Scan(Vein[] veins) {
        this.scan = new BST<>();
        Bounds b = new Bounds(SPRING, SPRING);
        for (Vein vein : veins) {
            b = b.plus(vein.bounds());
            for (Point p : vein.points()) {
                scan.put(p, CLAY);
            }
        }
        this.bounds = b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int mx = bounds.max().x + 1;
        int my = bounds.max().y;
        for (int y = 0; y <= my; y++) {
            if (y > 0) sb.append('\n');
            for (int x = bounds.min().x - 1; x <= mx; x++) {
                Point p = new Point(x, y);
                Character c = scan.get(p);
                sb.append(c == null ? p.equals(SPRING) ? '+' : '.' : c);
            }
        }
        return sb.toString();
    }
}

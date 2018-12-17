package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Bounds;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.TreeSet;

class Earth {

    static Earth parse(String input) {
        String[] lines = input.trim().split("\n");
        TreeSet<Point> clays = new TreeSet<>();
        Point spring = null;
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == SPRING) {
                    spring = new Point(x, y);
                } else if (c == CLAY) {
                    clays.add(new Point(x, y));
                }
            }
        }
        if (spring == null) {
            throw new IllegalArgumentException("The spring's location must be provided (w/ '+')");
        }
        return new Earth(clays, spring);
    }

    static final char CLAY = '#';
    static final char SAND = '.';
    static final char SPRING = '+';
    static final char RUNNING = '|';
    static final char POOL = '~';

    private static final int UNBOUNDED = 9999999;

    private final BST<Point, Character> scan;
    final Point spring;
    private final Bounds bounds;
    private final int maxY;

    Earth(Vein[] clays) {
        this(clays, new Point(500, 0));
    }

    Earth(Vein[] clays, Point spring) {
        this.scan = new BST<>();
        this.spring = spring;
        scan.put(spring, SPRING);
        Bounds b = null;
        for (Vein vein : clays) {
            b = b == null ? vein.bounds() : b.plus(vein.bounds());
            for (Point p : vein.points()) {
                scan.put(p, CLAY);
            }
        }
        this.bounds = b;
        assert bounds != null;
        this.maxY = bounds.max().y;
    }

    Earth(TreeSet<Point> clays, Point spring) {
        this.scan = new BST<>();
        this.spring = spring;
        scan.put(spring, SPRING);
        Bounds b = null;
        for (Point p : clays) {
            b = b == null ? new Bounds(p, p) : b.plus(p);
            scan.put(p, CLAY);
        }
        this.bounds = b;
        assert bounds != null;
        this.maxY = bounds.max().y;
    }

    void runWater() {
        runWater(spring);
    }

    private void runWater(Point src) {
        int level = src.y;
        while (true) {
            Point p = new Point(src.x, level);
            Character c = scan.get(p);
            if (c == null) {
                scan.put(p, RUNNING);
            } else if (c == CLAY || c == POOL) {
                level -= 1;
                break;
            }
            if (level++ == maxY) return;
        }
        int leftEdge = flood(src.x, level, -1);
        int rightEdge = flood(src.x, level, 1);
        if (leftEdge != UNBOUNDED && rightEdge != UNBOUNDED) {
            // this level is bounded
            for (int x = leftEdge; x <= rightEdge; x++) {
                scan.put(new Point(x, level), POOL);
            }
            // run some more water in it!
            runWater(new Point(src.x, level - 1));
        }
    }

    // i return either the dir-most edge of the pool or UNBOUNDED if it spills
    private int flood(int x, final int level, final int dx) {
        x += dx; // don't want to process the "dripped" tile
        for (; true; x += dx) {
            // check for bound
            Point p = new Point(x, level);
            Character c = scan.get(p);
            if (c != null && c == CLAY) {
                return x - dx;
            }
            scan.put(p, RUNNING);

            // check for floor
            p = new Point(x, level + 1);
            c = scan.get(p);
            if (c == null) {
                // we can drip!
                runWater(p);
                return UNBOUNDED;
            }
        }
    }

    int wetTiles() {
        int n = 0;
        for (Point p : scan.keys()) {
            if (! p.within(bounds)) continue;
            char c = scan.get(p);
            if (c == RUNNING || c == POOL) {
                n += 1;
            }
        }
        return n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int mx = bounds.max().x + 1;
        for (int y = 0; y <= maxY; y++) {
            if (y > 0) sb.append('\n');
            for (int x = bounds.min().x - 1; x <= mx; x++) {
                Point p = new Point(x, y);
                Character c = scan.get(p);
                sb.append(c == null ? p.equals(spring) ? SPRING : SAND : c);
            }
        }
        return sb.toString();
    }
}

package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Bounds;
import com.barneyb.aoc2018.util.Dir;
import com.barneyb.aoc2018.util.Point;

import static com.barneyb.aoc2018.util.Dir.LEFT;
import static com.barneyb.aoc2018.util.Dir.RIGHT;

class Earth {

    public static final Point LOC_SPRING = new Point(500, 0);

    static final char CLAY = '#';
    static final char SAND = '.';
    static final char SPRING = '+';
    static final char RUNNING = '|';
    static final char POOL = '~';

    private static final int UNBOUNDED = 9999999;

    private final BST<Point, Character> scan;
    private final Bounds bounds;
    private final int maxY;

    Earth(Vein[] clays) {
        this.scan = new BST<>();
        scan.put(LOC_SPRING, SPRING);
        Bounds b = null;
        for (Vein vein : clays) {
            if (b == null) {
                b = vein.bounds();
            } else {
                b = b.plus(vein.bounds());
            }
            for (Point p : vein.points()) {
                scan.put(p, CLAY);
            }
        }
        this.bounds = b;
        this.maxY = bounds.max().y;
    }

    void runWater() {
        runWater(LOC_SPRING);
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
        int leftEdge = flood(src.x, level, LEFT);
        int rightEdge = flood(src.x, level, RIGHT);
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
    private int flood(int x, final int level, Dir dir) {
        final int dx = dir.delta().x;
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
                sb.append(c == null ? p.equals(LOC_SPRING) ? SPRING : SAND : c);
            }
        }
        return sb.toString();
    }
}

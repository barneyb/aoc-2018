package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

class Map {

    private static final int X_FACTOR = 16807;
    private static final int Y_FACTOR = 48271;
    static final Point ORIGIN = new Point(0, 0);

    static Map parse(String input) {
        Scanner s = new Scanner(input);
        int depth = s.skip("depth:").skipWS().readInt();
        s.skipWS().skip("target:").skipWS();
        Point p = Point.scan(s);
        return new Map(depth, p);
    }

    private final int depth;
    private final Point target;
    private final BST<Point, Region> map;

    Map(int depth, Point target) {
        this.depth = depth;
        this.target = target;
        map = new BST<>();
    }

    int depth() {
        return depth;
    }

    Point target() {
        return target;
    }

    Region region(int x, int y) {
        return region(new Point(x, y));
    }

    Region region(Point p) {
        Region r = map.get(p);
        if (r != null) return r;
        int g = p.x == 0 && p.y == 0
                ? 0
                : p.equals(target)
                ? 0
                : p.y == 0
                ? p.x * X_FACTOR
                : p.x == 0
                ? p.y * Y_FACTOR
                : region(p.go(Dir.UP)).erosionLevel() * region(p.go(Dir.LEFT)).erosionLevel();
        int e = (g + depth) % 20183;
        r = new Region(g, e);
        map.put(p, r);
        return r;
    }

    int riskLevel() {
        return riskLevel(target);
    }

    int riskLevel(Point to) {
        return riskLevel(new Bounds(
                new Point(0, 0),
                to
        ));
    }

    int riskLevel(Bounds b) {
        AtomicInteger ai = new AtomicInteger();
        digest(b, p -> ai.addAndGet(region(p).riskLevel()));
        return ai.intValue();
    }

    @Override
    public String toString() {
        return toString(target);
    }

    String toString(Point to) {
        return toString(new Bounds(
                ORIGIN,
                to
        ));
    }

    String toString(Bounds b) {
        StringBuilder sb = new StringBuilder();
        int minY = b.min().y;
        digest(b, y -> {
            if (y > minY) sb.append('\n');
        }, p -> {
            if (p.equals(ORIGIN)) sb.append('M');
            else if (p.equals(target)) sb.append('T');
            else sb.append(region(p).type().indicator());
        });
        return sb.toString();
    }

    private void digest(Bounds b, Consumer<Point> pointWork) {
        digest(b, null, pointWork);
    }

    private void digest(Bounds b, Consumer<Integer> rowWork, Consumer<Point> pointWork) {
        for (int y = b.min().y, my = b.max().y; y <= my; y++) {
            if (rowWork != null) rowWork.accept(y);
            for (int x = b.min().x, mx = b.max().x; x <= mx; x++) {
                pointWork.accept(new Point(x, y));
            }
        }
    }
}

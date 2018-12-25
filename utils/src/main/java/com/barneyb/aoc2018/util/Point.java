package com.barneyb.aoc2018.util;

public class Point implements Comparable<Point> {

    public static Point parse(String input) {
        return scan(new Scanner(input));
    }

    public static Point scan(Scanner s) {
        boolean hasParen = s.probe('(');
        if (hasParen) s.skip('(');
        Point p = new Point(
                s.skipWS().readInt(),
                s.skip(',').skipWS().readInt()
        );
        if (hasParen) s.skip(')');
        return p;
    }

    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int md(Point p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }

    public Point plus(Point p) {
        return new Point(
                x + p.x,
                y + p.y
        );
    }

    public Point minus(Point p) {
        return new Point(
                x - p.x,
                y - p.y
        );
    }

    public Point times(int scalar) {
        return new Point(
                x * scalar,
                y * scalar
        );
    }

    public boolean adjacent(Point p) {
        if (p.x == x) return Math.abs(p.y - y) == 1;
        if (p.y == y) return Math.abs(p.x - x) == 1;
        return false;
    }

    public boolean within(Bounds b) {
        return x >= b.min().x && x <= b.max().x
                && y >= b.min().y && y <= b.max().y;
    }

    public int index(int width) {
        return y * width + x;
    }

    public static Point fromIndex(int i, int width) {
        return new Point(i % width, i / width);
    }

    public Point go(Dir d) {
        return go(d, 1);
    }

    public Point go(Dir d, int n) {
        return plus(d.delta(n));
    }

    /**
     * I provide "english reading" ordering of points. Origin is at the top
     * left corner, x increases to the right, and y increases downward.
     */
    @Override
    public int compareTo(Point p) {
        if (y < p.y) return -1;
        if (y > p.y) return 1;
        //noinspection UseCompareMethod
        if (x < p.x) return -1;
        if (x > p.x) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

}

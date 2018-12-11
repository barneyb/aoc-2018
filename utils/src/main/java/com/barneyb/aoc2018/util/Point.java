package com.barneyb.aoc2018.util;

public class Point implements Comparable<Point> {

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

    @Override
    public int compareTo(Point p) {
        if (x < p.x) return -1;
        if (x > p.x) return 1;
        if (y < p.y) return -1;
        if (y > p.y) return 1;
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

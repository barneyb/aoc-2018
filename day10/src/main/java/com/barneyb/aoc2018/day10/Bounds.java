package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Point;

public class Bounds {

    final Point min;
    final Point max;

    public Bounds(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public int width() {
        return max.x - min.x + 1;
    }

    public int height() {
        return max.y - min.y + 1;
    }

    public int area() {
        return width() * height();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bounds)) return false;

        Bounds bounds = (Bounds) o;

        if (!min.equals(bounds.min)) return false;
        return max.equals(bounds.max);
    }

    @Override
    public int hashCode() {
        int result = min.hashCode();
        result = 31 * result + max.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Bounds{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}

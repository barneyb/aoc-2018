package com.barneyb.aoc2018.util;

public enum Dir {

    // from a given Point, these will return positive deltas in reading order
    UP(new Point(0, -1)),
    LEFT(new Point(-1, 0)),
    RIGHT(new Point(1, 0)),
    DOWN(new Point(0, 1));

    private final Point delta;

    private Dir(Point delta) {
        this.delta = delta;
    }

    public Point delta() {
        return delta;
    }

    public Point delta(int steps) {
        if (steps == 1) return delta();
        return delta.times(steps);
    }

}

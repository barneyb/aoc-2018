package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;

public enum Dir {

    NORTH('^', new Point(0, -1)),
    SOUTH('v', new Point(0, 1)),
    EAST('>', new Point(1, 0)),
    WEST('<', new Point(-1, 0));

    final char indicator;
    final Point delta;

    Dir(char indicator, Point delta) {
        this.indicator = indicator;
        this.delta = delta;
    }

}

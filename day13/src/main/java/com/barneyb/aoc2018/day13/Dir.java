package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;

import static com.barneyb.aoc2018.day13.Turn.RIGHT;
import static com.barneyb.aoc2018.day13.Turn.STRAIGHT;

enum Dir {

    NORTH('^', new Point(0, -1)),
    SOUTH('v', new Point(0, 1)),
    EAST('>', new Point(1, 0)),
    WEST('<', new Point(-1, 0));

    final char indicator;
    final Point step;

    Dir(char indicator, Point step) {
        this.indicator = indicator;
        this.step = step;
    }

    Dir curve(char at) {
        switch (this) {
            case NORTH:
                return at == '/' ? EAST : WEST;
            case SOUTH:
                return at == '/' ? WEST : EAST;
            case EAST:
                return at == '/' ? NORTH : SOUTH;
            case WEST:
                return at == '/' ? SOUTH : NORTH;
        }
        throw new RuntimeException("Unknown direction: " + this);
    }

    Dir turn(Turn t) {
        if (t == STRAIGHT) return this;
        switch (this) {
            case NORTH:
                return t == RIGHT ? EAST : WEST;
            case SOUTH:
                return t == RIGHT ? WEST : EAST;
            case EAST:
                return t == RIGHT ? SOUTH : NORTH;
            case WEST:
                return t == RIGHT ? NORTH : SOUTH;
        }
        throw new RuntimeException("Unknown direction: " + this);
    }

}

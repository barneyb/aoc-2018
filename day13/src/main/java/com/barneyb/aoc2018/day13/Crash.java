package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;

class Crash {

    final Point loc;
    final char a, b;

    Crash(Point loc, char a, char b) {
        this.loc = loc;
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return String.format("Crash{%s and %s at (%d,%d)}", a, b, loc.x, loc.y);
    }
}

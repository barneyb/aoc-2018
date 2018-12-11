package com.barneyb.aoc2018.day11;

import com.barneyb.aoc2018.util.Point;

public class Square {

    final Point p;
    final int leg;

    public Square(Point p, int leg) {
        this.p = p;
        this.leg = leg;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%d", p.x, p.y, leg);
    }
}

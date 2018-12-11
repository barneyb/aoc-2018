package com.barneyb.aoc2018.day11;

import com.barneyb.aoc2018.util.Point;

public class Cell extends Point {

    public Cell(int x, int y) {
        super(x, y);
    }

    int level(int serial) {
        int rackId = x + 10;
        int level = rackId * y;
        level += serial;
        level *= rackId;
        level /= 100;
        level %= 10;
        return level - 5;
    }

}

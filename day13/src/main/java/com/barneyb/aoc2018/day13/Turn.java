package com.barneyb.aoc2018.day13;

enum Turn {

    LEFT,
    STRAIGHT,
    RIGHT;

    Turn next() {
        Turn[] vs = Turn.values();
        return vs[(ordinal() + 1) % vs.length];
    }

}

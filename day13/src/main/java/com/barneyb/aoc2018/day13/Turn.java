package com.barneyb.aoc2018.day13;

public enum Turn {

    LEFT,
    STRAIGHT,
    RIGHT;

    public Turn next() {
        Turn[] vs = Turn.values();
        return vs[(ordinal() + 1) % vs.length];
    }

}

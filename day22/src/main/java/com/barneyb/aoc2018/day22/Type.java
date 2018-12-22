package com.barneyb.aoc2018.day22;

enum Type {

    ROCKY('.'),
    WET('='),
    NARROW('|');

    static Type forErosionLevel(int el) {
        return Type.values()[el % 3];
    }

    final char indicator;

    Type(char indicator) {
        this.indicator = indicator;
    }

    int riskLevel() {
        return ordinal();
    }

}

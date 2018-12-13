package com.barneyb.aoc2018.day13;

public enum Dir {

    NORTH('^'),
    SOUTH('v'),
    EAST('>'),
    WEST('<');

    final char indicator;

    Dir(char indicator) {
        this.indicator = indicator;
    }

}

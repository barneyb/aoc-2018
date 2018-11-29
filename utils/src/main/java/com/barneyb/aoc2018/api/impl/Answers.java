package com.barneyb.aoc2018.api.impl;

final public class Answers {
    final String partOne;
    final String partTwo;

    public Answers(Object partOne, Object partTwo) {
        this.partOne = partOne.toString();
        this.partTwo = partTwo.toString();
    }

    public String getPartOne() {
        return partOne;
    }

    public String getPartTwo() {
        return partTwo;
    }
}

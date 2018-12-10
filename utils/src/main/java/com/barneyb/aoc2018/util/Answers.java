package com.barneyb.aoc2018.util;

final public class Answers {
    final String partOne;
    final String partTwo;

    public Answers(Object partOne) {
        this(partOne, "-");
    }

    public Answers(Object partOne, Object partTwo) {
        this.partOne = partOne == null ? "null" : partOne.toString();
        this.partTwo = partTwo == null ? "null" : partTwo.toString();
    }

    public String getPartOne() {
        return partOne;
    }

    public String getPartTwo() {
        return partTwo;
    }

    @Override
    public String toString() {
        return "Answers[" + partOne + ", " + partTwo + ']';
    }
}

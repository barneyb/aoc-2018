package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.Day;

abstract public class OneShotDay implements Day {

    private Answers answers;

    @Override
    public void setInput(String input) {
        this.answers = solve(input);
    }

    @Override
    public String getPartOne() {
        return answers.partOne;
    }

    @Override
    public String getPartTwo() {
        return answers.partTwo;
    }

    public abstract Answers solve(String input);
}

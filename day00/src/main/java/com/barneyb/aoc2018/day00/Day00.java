package com.barneyb.aoc2018.day00;

import com.barneyb.aoc2018.api.impl.Answers;
import com.barneyb.aoc2018.api.impl.OneShotDay;

public class Day00 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

}

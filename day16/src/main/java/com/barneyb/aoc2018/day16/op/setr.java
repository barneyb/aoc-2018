package com.barneyb.aoc2018.day16.op;

import com.barneyb.aoc2018.day16.Op;

public class setr implements Op {

    @Override
    public void execute(int[] registers, int a, int b, int c) {
        registers[c] = registers[a];
    }

}

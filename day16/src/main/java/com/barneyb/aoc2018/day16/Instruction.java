package com.barneyb.aoc2018.day16;

import com.barneyb.aoc2018.util.Scanner;

public class Instruction {

    public static Instruction parse(String input) {
        Scanner s = new Scanner(input);
        return new Instruction(
                s.skipWS().readInt(),
                s.skipWS().readInt(),
                s.skipWS().readInt(),
                s.skipWS().readInt()
        );
    }

    final int opcode, a, b, c;

    public Instruction(int opcode, int a, int b, int c) {
        this.opcode = opcode;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instruction)) return false;

        Instruction that = (Instruction) o;

        if (opcode != that.opcode) return false;
        if (a != that.a) return false;
        if (b != that.b) return false;
        return c == that.c;
    }

    @Override
    public int hashCode() {
        int result = opcode;
        result = 31 * result + a;
        result = 31 * result + b;
        result = 31 * result + c;
        return result;
    }
}

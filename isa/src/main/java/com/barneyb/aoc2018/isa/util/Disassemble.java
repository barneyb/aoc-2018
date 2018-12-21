package com.barneyb.aoc2018.isa.util;

public class Disassemble {

    public static String registerName(int r) {
        return new String(new char[]{(char) ('a' + r)});
    }

    public static String registerName(int ipr, int r) {
        return r == ipr ? "ip" : registerName(r);
    }

}

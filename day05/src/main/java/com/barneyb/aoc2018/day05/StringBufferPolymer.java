package com.barneyb.aoc2018.day05;

// both parts: ~550 ms
public class StringBufferPolymer implements Polymer {

    private StringBuffer polymer;

    public StringBufferPolymer(String polymer) {
        this.polymer = new StringBuffer(polymer);
    }

    public static StringBufferPolymer parse(String str) {
        return new StringBufferPolymer(str);
    }

    @Override
    public void reduce() {
        int a, b;
        for (int i = 0; i < polymer.length() - 1; i++) {
            a = polymer.charAt(i);
            b = polymer.charAt(i + 1);
            if (a - b == 32 || b - a == 32) {
                polymer.delete(i, i + 2);
                if (i > 0) {
                    i -= 2; // go back one step
                }
            }
        }
    }

    @Override
    public int length() {
        return polymer.length();
    }

    @Override
    public String toString() {
        return polymer.toString();
    }
}

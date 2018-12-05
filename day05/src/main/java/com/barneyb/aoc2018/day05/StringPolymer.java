package com.barneyb.aoc2018.day05;

public class StringPolymer implements Polymer {

    private String polymer;

    public StringPolymer(String polymer) {
        this.polymer = polymer;
    }

    public static StringPolymer parse(String str) {
        return new StringPolymer(str);
    }

    @Override
    public void reduce() {
        int a, b;
        for (int i = 0; i < polymer.length() - 1; i++) {
            a = polymer.charAt(i);
            b = polymer.charAt(i + 1);
            if (a - b == 32 || b - a == 32) {
                polymer = polymer.substring(0, i) + polymer.substring(i + 2);
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
        return polymer;
    }
}

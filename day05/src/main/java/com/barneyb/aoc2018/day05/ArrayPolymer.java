package com.barneyb.aoc2018.day05;

// both parts: ~500 ms
public class ArrayPolymer implements Polymer {

    private final char[] polymer;
    private int length;

    public ArrayPolymer(String polymer) {
        this.polymer = polymer.toCharArray();
        this.length = this.polymer.length;
    }

    public static ArrayPolymer parse(String str) {
        return new ArrayPolymer(str);
    }

    @Override
    public void reduce() {
        int a, b;
        for (int i = 0; i < length - 1; i++) {
            a = polymer[i];
            b = polymer[i + 1];
            if (a - b == 32 || b - a == 32) {
                deletePair(i);
                if (i > 0) {
                    i -= 2; // go back one step
                }
            }
        }
    }

    private void deletePair(int i) {
        if (i < length) {
            System.arraycopy(polymer, i + 2, polymer, i, length - i - 2);
        }
        length -= 2;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public String toString() {
        return new String(polymer, 0, length);
    }
}

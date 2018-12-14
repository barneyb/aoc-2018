package com.barneyb.aoc2018.util;

public class Arrays {

    public static boolean equals(int[] a, int[] b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        int l = a.length;
        if (l != b.length) return false;
        for (int i = 0; i < l; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

}

package com.barneyb.aoc2018.day02;

public class Utils {

    static boolean areNeighbors(String a, String b) {
        boolean foundDiff = false;
        for (int i = 0, l = a.length(); i < l; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (foundDiff) {
                    return false;
                }
                foundDiff = true;
            }
        }
        return foundDiff;
    }

    static String commonLetters(String a, String b) {
        StringBuilder s = new StringBuilder();
        for (int i = 0, l = a.length(); i < l; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                s.append(a.charAt(i));
            }
        }
        return s.toString();
    }

}

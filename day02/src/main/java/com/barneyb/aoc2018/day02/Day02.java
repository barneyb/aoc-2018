package com.barneyb.aoc2018.day02;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day02 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Warehouse w = Warehouse.parse(input);
        String[] ids = parse(input);
        return new Answers(
                w.checksum(),
                getPartTwo(ids)
        );
    }

    String getPartTwo(String[] ids) {
        for (int i = 0, l = ids.length; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (areNeighbors(ids[i], ids[j])) {
                    return commonLetters(ids[i], ids[j]);
                }
            }
        }
        throw new RuntimeException("um, what?");
    }

    boolean areNeighbors(String a, String b) {
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

    String commonLetters(String a, String b) {
        StringBuilder s = new StringBuilder();
        for (int i = 0, l = a.length(); i < l; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                s.append(a.charAt(i));
            }
        }
        return s.toString();
    }

    private String[] parse(String input) {
        return input.trim().split("\n");
    }

    public static void main(String[] args)  {
        Day02 d = new Day02();
        String input = FileUtils.readFile("day02/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

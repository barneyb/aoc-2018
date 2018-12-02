package com.barneyb.aoc2018.day02;

import com.barneyb.aoc2018.api.impl.Answers;
import com.barneyb.aoc2018.api.impl.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

public class Day02 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String[] ids = parse(input);
        return new Answers(
                getPartOne(ids)
        );
    }

    int getPartOne(String[] ids) {
        Histogram<Character>[] hs = buildFrequencies(ids);
        int two = countWithFrequency(hs, 2);
        int three = countWithFrequency(hs, 3);
        return two * three;
    }

    private int countWithFrequency(Histogram<Character>[] hs, int i) {
        int n = 0;
        for (Histogram<Character> h : hs) {
            if (anyLetterWithFrequency(h, i)) {
                n += 1;
            }
        }
        return n;
    }

    private boolean anyLetterWithFrequency(Histogram<Character> h, int i) {
        for (Character c : h.keys()) {
            if (h.get(c) == i) {
                return true;
            }
        }
        return false;
    }

    private String[] parse(String input) {
        return input.trim().split("\n");
    }

    Histogram<Character>[] buildFrequencies(String[] strings) {
        @SuppressWarnings("unchecked")
        Histogram<Character>[] hs = new Histogram[strings.length];
        for (int i = 0, l = strings.length; i < l; i++) {
            hs[i] = Histogram.fromString(strings[i]);
        }
        return hs;
    }

    public static void main(String[] args)  {
        Day02 d = new Day02();
        String input = FileUtils.readFile("day02/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

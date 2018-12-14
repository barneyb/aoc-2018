package com.barneyb.aoc2018.day14;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Arrays;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day14 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        input = input.trim();
        return new Answers(
                partOne(input),
                partTwo(input)
        );
    }

    static int partTwo(String input) {
        int[] tgt = new int[input.length()];
        for (int i = 0, l = input.length(); i < l; i++) {
            tgt[i] = Character.digit(input.charAt(i), 10);
        }
        int[] cmp = new int[tgt.length];
        Sim s = new Sim();
        while (true) {
            s.tick();
            int rc = s.recipeCount();
            if (rc < tgt.length) continue;
            // this will redo a check on a single-digit "next" score, but meh.
            for (int d = Math.min(1, rc - input.length()); d >= 0; d--) {
                int cutoff = rc - input.length() - d;
                for (int i = 0; i < tgt.length; i++) {
                    cmp[i] = s.scoreAt(i + cutoff);
                }
                if (Arrays.equals(tgt, cmp)) {
                    return cutoff;
                }
            }

        }
    }

    static String partOne(String input) {
        int goal = Integer.parseInt(input);
        Sim s = new Sim();
        while (s.recipeCount() < goal + 11) {
            s.tick();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s.scoreAt(goal + i));
        }
        return sb.toString();
    }

    public static void main(String[] args)  {
        Day14 d = new Day14();
        String input = FileUtils.readFile("day14/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

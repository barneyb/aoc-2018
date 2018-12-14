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
        Sim s = new Sim();
        // get these over with
        while (s.recipeCount() < tgt.length) s.tick();
        // start checking
        int[] cmp = new int[tgt.length];
        int lenMinusOne = cmp.length - 1;
        int cutoff = 0;
        while (true) {
            s.tick();
            for (int l = s.recipeCount() - tgt.length; cutoff < l; cutoff++) {
                // slide over by one
                System.arraycopy(cmp, 1, cmp, 0, lenMinusOne);
                // update the final slot
                cmp[lenMinusOne] = s.scoreAt(cutoff + lenMinusOne);
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

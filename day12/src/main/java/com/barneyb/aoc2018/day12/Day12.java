package com.barneyb.aoc2018.day12;

import com.barneyb.aoc2018.util.*;

public class Day12 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String[] lines = input.trim().split("\n");
        Bag<String> growers = new Bag<>();
        String state = new Scanner(lines[0]).skip("initial state: ").rest();
        for (int i = 2, l = lines.length; i < l; i++) {
            if (lines[i].endsWith("#")) {
                growers.add(new Scanner(lines[i]).readWord());
            }
        }
        return new Answers(
                getPotSum(state, growers, 20)
                , getPotSum(state, growers, 50000000000L)
        );
    }

    static long getPotSum(String state, Bag<String> growers, final long generations) {
        Pots pots = new Pots(state, growers);
        long cutoff = generations;
        for (long i = 0; ; i++) {
//            if (i % 5 == 0 || pots.stable()) {
//                System.out.printf("%4d (%3d): ", i, pots.offset());
//                for (int j = -3; j < pots.offset(); j++) {
//                    System.out.print(".");
//                }
//                System.out.print(pots.state().replace(' ', '.'));
//                for (int j = pots.offset() + pots.state().length(); j < 36; j++) {
//                    System.out.print(".");
//                }
//                System.out.println();
//            }
            if (i == cutoff) {
//                System.out.println("finished at " + i + " (of " + generations + ")");
                break;
            }
            pots.tick();
            if (pots.stable() && cutoff > i + 5) {
                cutoff = i + 5;
            }
        }
//        System.out.println(pots.plantCount() + " plants after " + generations + " gens");
        long sum = 0;
        for (int pn : pots.potsWithPlants()) {
            sum += pn;
        }
        sum += pots.plantCount() * (generations - cutoff);
        return sum;
    }

    public static void main(String[] args)  {
        Day12 d = new Day12();
        String input = FileUtils.readFile("day12/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

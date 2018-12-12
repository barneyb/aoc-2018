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
                getPotSum(state, growers, 20),
                getPotSum(state, growers, 50000000000L / 100000)
        );
    }

    private long getPotSum(String state, Bag<String> growers, long generations) {
        Pots pots = new Pots(state, growers);
//        System.out.println("                 1         2         3");
//        System.out.println("       0         0         0         0");
        for (long i = 0; ; i++) {
//            System.out.printf("%2d: ", i);
//            for (int j = -3; j < pots.offset(); j++) {
//                System.out.print(".");
//            }
//            System.out.print(pots.state());
//            for (int j = pots.offset() + pots.state().length(); j < 36; j++) {
//                System.out.print(".");
//            }
//            System.out.println();
            if (i == generations) {
                break;
            }
            pots.tick();
        }
        System.out.println(pots.state().length() + " pots after " + generations + " gens");
        long sum = 0;
        for (int pn : pots.potsWithPlants()) {
            sum += pn;
        }
        return sum;
    }

    public static void main(String[] args)  {
        Day12 d = new Day12();
        String input = FileUtils.readFile("day12/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

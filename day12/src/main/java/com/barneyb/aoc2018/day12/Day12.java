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
        Pots pots = new Pots(state, growers);
//        System.out.println("                 1         2         3");
//        System.out.println("       0         0         0         0");
        for (int i = 0; ; i++) {
//            System.out.printf("%2d: ", i);
//            for (int j = -3; j < pots.offset(); j++) {
//                System.out.print(".");
//            }
//            System.out.print(pots.state());
//            for (int j = pots.offset() + pots.state().length(); j < 36; j++) {
//                System.out.print(".");
//            }
//            System.out.println();
            if (i == 20) {
                break;
            }
            pots.tick();
        }
        int sum = 0;
        for (int pn : pots.potsWithPlants()) {
            sum += pn;
        }
        return new Answers(
                sum
        );
    }

    public static void main(String[] args)  {
        Day12 d = new Day12();
        String input = FileUtils.readFile("day12/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

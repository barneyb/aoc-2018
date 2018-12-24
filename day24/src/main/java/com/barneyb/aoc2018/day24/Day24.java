package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.*;

public class Day24 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Scanner scanner = new Scanner(input.trim());
        Army immune = Army.scan(scanner);
        immune.label("Immune System");
        Army infection = Army.scan(scanner);
        infection.label("Infection");
        Combat c = new Combat(immune, infection);
        for (int f = 1; ! c.isOver(); f++) {
            System.out.println("Fight " + f);
            c.fight();
            System.out.println();
        }
        return new Answers(
                immune.alive() ? immune.units() : infection.units()
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day24 d = new Day24();
        String input = FileUtils.readFile("day24/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

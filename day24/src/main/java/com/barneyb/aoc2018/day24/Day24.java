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
        return new Answers(
                partOne(immune, infection)
                , partTwo(immune, infection)
        );
    }

    int partOne(Army immune, Army infection) {
        immune = immune.duplicate();
        infection = infection.duplicate();
        Combat c = new Combat(immune, infection);
        c.run();
        return immune.alive() ? immune.units() : infection.units();
    }

    int partTwo(Army immune, Army infection) {
        Combat c;
        for (int boost = 1; true; boost++) {
            Army a = immune.duplicate();
            a.boost(boost);
            c = new Combat(a, infection.duplicate());
            c.run();
            if (! c.infected()) return a.units();
        }
    }

    // 25180 is too high
    // 4765 is too low

    public static void main(String[] args)  {
        Day24 d = new Day24();
        String input = FileUtils.readFile("day24/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

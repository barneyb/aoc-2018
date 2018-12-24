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
            Army immu = immune.duplicate();
            immu.boost(boost);
            Army infec = infection.duplicate();
            c = new Combat(immu, infec);
            boolean complete = c.run();
//            if (! complete) {
//                System.out.printf("stalemate? w/ %d boost (%d to %d)%n", boost, immu.units(), infec.units());
//            }
            if (! c.infected()) {
//                System.out.printf("Immune wins w/ %d boost and %d units %n", boost, immu.units());
                return immu.units();
            }
        }
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

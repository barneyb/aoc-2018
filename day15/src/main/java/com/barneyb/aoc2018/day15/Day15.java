package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day15 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Map m = Map.parse(input);
        return new Answers(
                partOne(m).result()
//                , input.trim().length()
        );
    }

    static class PartOneResult {
        private final int rounds;
        private Iterable<Unit> units;
        private final int hitPoints;

        public PartOneResult(int rounds, Iterable<Unit> units) {
            this.rounds = rounds;
            this.units = units;
            int hp = 0;
            for (Unit u : units) {
                hp += u.hitPoints();
            }
            this.hitPoints = hp;
        }

        int rounds() {
            return rounds;
        }

        Iterable<Unit> units() {
            return units;
        }

        int hitPoints() {
            return hitPoints;
        }

        int result() {
            return rounds * hitPoints;
        }

    }

    static PartOneResult partOne(Map m) {
        Engine s = new Engine(m);
        s.run();
        return new PartOneResult(s.rounds(), m.livingUnits());
    }

    // 203931 is too high!

    public static void main(String[] args)  {
        Day15 d = new Day15();
        String input = FileUtils.readFile("day15/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

import static com.barneyb.aoc2018.day15.Map.ELF;
import static com.barneyb.aoc2018.day15.Map.GOBLIN;

public class Day15 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                partOne(input).result()
//                , input.trim().length()
        );
    }

    static class Result {
        private final char winner;
        private final int rounds;
        private Iterable<Unit> units;
        private final int hitPoints;

        public Result(int rounds, Iterable<Unit> units) {
            this.winner = units.iterator().next().isGoblin() ? GOBLIN : ELF;
            this.rounds = rounds;
            this.units = units;
            int hp = 0;
            for (Unit u : units) {
                hp += u.hitPoints();
            }
            this.hitPoints = hp;
        }

        char winner() {
            return winner;
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

    static Result partOne(String input) {
        return partOne(Map.parse(input));
    }

    static Result partOne(Map m) {
        Engine s = new Engine(m);
        s.run();
        return new Result(s.rounds(), m.livingUnits());
    }

    // 203931 is too high!
    // 198855 is too high!

    public static void main(String[] args)  {
        Day15 d = new Day15();
        String input = FileUtils.readFile("day15/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

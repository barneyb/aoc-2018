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
                , partTwo(input).result()
        );
    }

    static class ArmedResult {
        private final Result result;
        private final int elvishAttack;
        private final Map map;

        ArmedResult(Result result, int elvishAttack, Map map) {
            this.result = result;
            this.elvishAttack = elvishAttack;
            this.map = map;
        }

        int elvishAttack() {
            return elvishAttack;
        }

        int rounds() {
            return result.rounds();
        }

        int result() {
            return result.result();
        }

        Map map() {
            return map;
        }

    }

    static ArmedResult partTwo(String input) {
        for (int attack = 4; ; attack++) {
            Map m = Map.parse(input);
            m.armElves(attack);
            m.disallowDeadElves();
            // todo: this is a stupid approach, but it does work.
            try {
                return new ArmedResult(partOne(m), attack, m);
            } catch (DeadElfException ignored) {
                // and on to the next!
            }
        }
    }

    static class Result {
        private final char winner;
        private final int rounds;
        private Iterable<Unit> survivors;
        private Iterable<Unit> casualties;
        private final int hitPoints;

        public Result(int rounds, Iterable<Unit> survivors, Iterable<Unit> casualties) {
            this.winner = survivors.iterator().next().isGoblin() ? GOBLIN : ELF;
            this.rounds = rounds;
            this.survivors = survivors;
            this.casualties = casualties;
            int hp = 0;
            for (Unit u : survivors) {
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

        Iterable<Unit> survivors() {
            return survivors;
        }

        Iterable<Unit> casualties() {
            return casualties;
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
        return new Result(s.rounds(), m.survivors(), m.casualties());
    }

    // part one
    // 203931 is too high!
    // 198855 is too high!
    // part two
    // 93666 is too high
    // 95546 is too high

    public static void main(String[] args)  {
        Day15 d = new Day15();
        String input = FileUtils.readFile("day15/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

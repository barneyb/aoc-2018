package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.*;

public class Day04 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Queue<Nap> naps = parseNaps(input);
        return new Answers(
                partOne(naps),
                partTwo(naps)
        );
    }

    private int partTwo(Queue<Nap> naps) {
        Histogram<Integer> guardMinutes = new Histogram<>();
        for (Nap n : naps) {
            for (int i = n.getStart(), l = n.getEnd(); i < l; i++) {
                guardMinutes.count(guardMinute(n.getGuardId(), i));
            }
        }
        return guardMinuteProduct(guardMinutes.mostFrequent());
    }

    static int guardMinuteProduct(int gm) {
        return (gm >> 8) * (gm & 0xFF);
    }

    static int guardMinute(int guardId, int minute) {
        return (guardId << 8) + minute;
    }

    private int partOne(Queue<Nap> naps) {
        Integer id = sleepiestGuard(naps);
        Integer minute = sleepiestMinute(naps, id);
        return id * minute;
    }

    static Integer sleepiestMinute(Queue<Nap> naps, Integer guardId) {
        Histogram<Integer> minutes = new Histogram<>();
        for (Nap n : naps) {
            if (n.getGuardId() == guardId) {
                for (int i = n.getStart(), l = n.getEnd(); i < l; i++) {
                    minutes.count(i);
                }
            }
        }
        return minutes.mostFrequent();
    }

    static Integer sleepiestGuard(Queue<Nap> naps) {
        Histogram<Integer> guards = new Histogram<>();
        for (Nap n : naps) {
            guards.add(n.getGuardId(), n.getLength());
        }
        return guards.mostFrequent();
    }

    static Queue<Nap> parseNaps(String input) {
        String[] lines = input.trim().split("\n");
        Sort.sort(lines);
        Queue<Nap> naps = new Queue<>();
        int asleepAt = 0;
        int thisGuard = 0;
        for (String line : lines) {
            String[] words = line.substring(15).split(" ");
            switch (words[1]) {
                case "Guard":
                    thisGuard = Integer.parseInt(words[2].substring(1));
                    break;
                case "falls":
                    asleepAt = Integer.parseInt(words[0].substring(0, 2));
                    break;
                case "wakes":
                    naps.enqueue(new Nap(
                            thisGuard,
                            asleepAt,
                            Integer.parseInt(words[0].substring(0, 2))
                    ));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown word: '" + words[1] + "'");
            }
        }
        return naps;
    }

}

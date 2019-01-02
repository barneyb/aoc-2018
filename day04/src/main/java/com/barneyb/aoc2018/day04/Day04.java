package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.Histogram;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day04 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Log l = Log.parse(input);
        return new Answers(
                partOne(l.naps()),
                partTwo(l.naps())
        );
    }

    private int partTwo(Iterable<Nap> naps) {
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

    private int partOne(Iterable<Nap> naps) {
        Integer id = sleepiestGuard(naps);
        Integer minute = sleepiestMinute(naps, id);
        return id * minute;
    }

    static Integer sleepiestMinute(Iterable<Nap> naps, Integer guardId) {
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

    static Integer sleepiestGuard(Iterable<Nap> naps) {
        Histogram<Integer> guards = new Histogram<>();
        for (Nap n : naps) {
            guards.add(n.getGuardId(), n.getLength());
        }
        return guards.mostFrequent();
    }

    public static void main(String[] args)  {
        Day04 d = new Day04();
        String input = FileUtils.readFile("day04/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

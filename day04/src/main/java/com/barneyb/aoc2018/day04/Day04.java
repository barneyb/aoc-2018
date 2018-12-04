package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.*;

public class Day04 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Record[] rs = parse(input);
        Queue<Nap> naps = getNaps(rs);
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

    static Queue<Nap> getNaps(Record[] rs) {
        Queue<Nap> naps = new Queue<>();
        Timestamp asleepAt = null;
        Integer thisGuard = null;
        for (Record r : rs) {
            switch (r.getAction()) {
                case BEGIN:
                    thisGuard = r.getGuardId();
                    break;
                case FALL_ASLEEP:
                    asleepAt = r.getTs();
                    break;
                case WAKE_UP:
                    //noinspection ConstantConditions
                    naps.enqueue(new Nap(
                            thisGuard,
                            asleepAt.getMinute(),
                            r.getTs().getMinute()
                    ));
                    break;
            }
        }
        return naps;
    }

    public static Record[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Record[] rs = new Record[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            rs[i] = Record.fromString(lines[i]);
        }
        Sort.sort(rs);
        return rs;
    }

    public static void main(String[] args)  {
        Day04 d = new Day04();
        String input = FileUtils.readFile("day04/input.txt");
        Record[] rs = parse(input);
        System.out.println("Before:");
        for (int i = 0; i < 20; i++) {
            System.out.println(rs[i]);
        }
        Stopwatch w = new Stopwatch();
        Sort.sort(rs);
        w.stop();
        System.out.println("After (" + w.elapsed() + " ms):");
        for (int i = 0; i < 20; i++) {
            System.out.println(rs[i]);
        }
    }

}

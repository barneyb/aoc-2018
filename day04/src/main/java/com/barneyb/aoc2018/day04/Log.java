package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.api.Collection;
import com.barneyb.aoc2018.util.Histogram;
import com.barneyb.aoc2018.util.Queue;
import com.barneyb.aoc2018.util.Sort;

class Log {

    static Log parse(String input) {
        String[] lines = input.trim().split("\n");
        Record[] rs = new Record[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            rs[i] = Record.parse(lines[i]);
        }
        return new Log(rs);
    }

    private final Record[] records;
    private final Queue<Nap> naps;

    public Log(Record[] records) {
        Sort.natural(records); // offense is the best defense!
        this.records = records;
        naps = new Queue<>();
        Timestamp asleepAt = null;
        Integer thisGuard = null;
        for (Record r : records) {
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
    }

    Record[] records() {
        return records;
    }

    Collection<Nap> naps() {
        return naps;
    }

    public int sleepiestGuardMinute() {
        int id = sleepiestGuard();
        return id * sleepiestMinute(id);
    }

    public int sleepiestMinuteGuard() {
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

    int sleepiestMinute(int guardId) {
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

    int sleepiestGuard() {
        Histogram<Integer> guards = new Histogram<>();
        for (Nap n : naps) {
            guards.add(n.getGuardId(), n.getLength());
        }
        return guards.mostFrequent();
    }

}

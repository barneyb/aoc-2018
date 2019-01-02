package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.api.Collection;
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

    public Record[] records() {
        return records;
    }

    public Collection<Nap> naps() {
        return naps;
    }

}

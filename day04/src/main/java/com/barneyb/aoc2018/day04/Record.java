package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Scanner;

public class Record implements Comparable<Record> {

    public static Record parse(String str) {
        return scan(new Scanner(str));
    }

    public static Record scan(Scanner s) {
        s.skip("[");
        Timestamp ts = Timestamp.scan(s);
        s.skip("] ");
        String prefix = s.readWord();
        switch (prefix) {
            case "Guard":
                return new Record(
                        ts,
                        Action.BEGIN,
                        s.skip("#").readInt()
                );
            case "falls":
                return new Record(
                        ts,
                        Action.FALL_ASLEEP
                );
            case "wakes":
                return new Record(
                        ts,
                        Action.WAKE_UP
                );
            default:
                throw new IllegalArgumentException("Unrecognized record prefix: '" + prefix + "'");
        }
    }

    private final Timestamp ts;
    private final Action action;
    private Integer guardId;

    public Record(Timestamp ts, Action action) {
        this.ts = ts;
        this.action = action;
    }

    public Record(Timestamp ts, Action action, int guardId) {
        this.ts = ts;
        this.action = action;
        this.guardId = guardId;
    }

    public Timestamp getTs() {
        return ts;
    }

    public Action getAction() {
        return action;
    }

    public Integer getGuardId() {
        return guardId;
    }

    public void setGuardId(Integer guardId) {
        this.guardId = guardId;
    }

    @Override
    public int compareTo(Record o) {
        return ts.compareTo(o.ts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;
        if (!ts.equals(record.ts)) return false;
        if (action != record.action) return false;
        return guardId != null ? guardId.equals(record.guardId) : record.guardId == null;
    }

    @Override
    public int hashCode() {
        int result = ts.hashCode();
        result = 31 * result + action.hashCode();
        result = 31 * result + (guardId != null ? guardId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        switch (action) {
            case BEGIN:
                return "[" + ts + "] Guard #" + guardId + " begins shift";
            case FALL_ASLEEP:
                return "[" + ts + "] falls asleep";
            case WAKE_UP:
                return "[" + ts + "] wakes up";
            default:
                throw new IllegalStateException("Unrecognized action '" + action + "'");
        }
    }
}

package com.barneyb.aoc2018.day04;

final class GuardMinute implements Comparable<GuardMinute> {

    private final int guardId;
    private final int minute;

    GuardMinute(int guardId, int minute) {
        this.guardId = guardId;
        this.minute = minute;
    }

    public int product() {
        return guardId * minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuardMinute)) return false;

        GuardMinute that = (GuardMinute) o;

        if (guardId != that.guardId) return false;
        return minute == that.minute;
    }

    @Override
    public int hashCode() {
        int result = guardId;
        result = 31 * result + minute;
        return result;
    }

    @Override
    public int compareTo(GuardMinute o) {
        int c = guardId - o.guardId;
        if (c != 0) return c;
        return minute - o.minute;
    }
}

package com.barneyb.aoc2018.day04;

public class Nap {

    private final int guardId, start, end;

    public Nap(int guardId, int start, int end) {
        this.guardId = guardId;
        this.start = start;
        this.end = end;
    }

    public int getGuardId() {
        return guardId;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength() {
        return end - start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nap)) return false;

        Nap nap = (Nap) o;

        if (guardId != nap.guardId) return false;
        if (start != nap.start) return false;
        return end == nap.end;
    }

    @Override
    public int hashCode() {
        int result = guardId;
        result = 31 * result + start;
        result = 31 * result + end;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Nap[%d-%d]", start, end);
    }
}

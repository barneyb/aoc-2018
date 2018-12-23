package com.barneyb.aoc2018.day23;

// half-open: contains min, but does NOT contain max
class Range implements Comparable<Range> {
    private final int min, max;

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int size() {
        return max - min;
    }

    public boolean contains(int i ) {
        return i >= min && i < max;
    }

    public boolean overlaps(Range r) {
        return contains(r.min) || contains(r.max);
    }

    public Range plus(Range r) {
        return new Range(
                Math.min(min, r.min),
                Math.max(max, r.max)
        );
    }

    public Range plus(int i) {
        if (contains(i)) return this;
        if (i < min) return new Range(i, max);
        return new Range(min, i + 1);
    }

    public double uniform(int n) {
        if (n < min) throw new IllegalArgumentException(n + " is less than " + min);
        if (n > max) throw new IllegalArgumentException(n + " is more than " + max);
        return 1.0 * (n - min) / size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range)) return false;
        Range range = (Range) o;
        if (min != range.min) return false;
        return max == range.max;
    }

    @Override
    public int hashCode() {
        int result = min;
        result = 31 * result + max;
        return result;
    }

    @Override
    public int compareTo(Range o) {
        return min == o.min ? max - o.max : min - o.min;
    }

    @Override
    public String toString() {
        return String.format("[%d..%d]", min, max);
    }

}
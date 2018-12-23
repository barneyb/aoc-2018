package com.barneyb.aoc2018.day23;

import java.util.Iterator;

// half-open: contains start, but does NOT contain end
class Range implements Comparable<Range>, Iterable<Integer> {

    public static final Range EMPTY = new Range(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public static Range inclusive(int a, int b) {
        return new Range(a, b + 1);
    }

    public static Range halfOpen(int a, int b) {
        return new Range(a, b);
    }

    public static Range exclusive(int a, int b) {
        return new Range(a + 1, b);
    }

    private final int start, end, step;

    private Range(int start, int end) {
        if (start > end) throw new IllegalArgumentException(start + " > " + end);
        this.start = start;
        this.end = end;
        this.step = 1;
    }
    private Range(int start, int end, int step) {
        if (start > end) throw new IllegalArgumentException(start + " > " + end);
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public int start() {
        if (this == EMPTY) throw new UnsupportedOperationException();
        return start;
    }

    public int end() {
        if (this == EMPTY) throw new UnsupportedOperationException();
        return end;
    }

    public int size() {
        if (this == EMPTY) throw new UnsupportedOperationException();
        return end - start;
    }

    public int steps() {
        if (this == EMPTY) throw new UnsupportedOperationException();
        return (int) Math.ceil(1.0 * size() / step);
    }

    public double scale(int n) {
        if (this == EMPTY) throw new UnsupportedOperationException();
        if (n < start) throw new IllegalArgumentException(n + " is < " + start);
        if (n >= end) throw new IllegalArgumentException(n + " is >= " + end);
        return 1.0 * (n - start) / size();
    }

    public int unscale(double d) {
        if (this == EMPTY) throw new UnsupportedOperationException();
        if (d < 0) throw new IllegalArgumentException(d + " is < 0");
        if (d >= 1) throw new IllegalArgumentException(d + " is >= 1");
        return (int) (d * size() + start);
    }

    public boolean contains(int i) {
        if (this == EMPTY) return false;
        return i >= start && i < end;
    }

    public boolean overlaps(Range r) {
        if (this == EMPTY) return false;
        return contains(r.start) || contains(r.end);
    }

    public Range plus(Range r) {
        if (this == EMPTY) return r;
        return new Range(
                Math.min(start, r.start),
                Math.max(end, r.end)
        );
    }

    public Range plus(int i) {
        if (this == EMPTY) return Range.inclusive(i, i);
        if (contains(i)) return this;
        if (i < start) return Range.halfOpen(i, end);
        return Range.inclusive(start, i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this == EMPTY) return false;
        if (o == EMPTY) return false;
        if (!(o instanceof Range)) return false;
        Range range = (Range) o;
        if (start != range.start) return false;
        return end == range.end;
    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + end;
        return result;
    }

    @Override
    public int compareTo(Range o) {
        if (this == EMPTY) return -1;
        if (o == EMPTY) return 1;
        return start == o.start ? end - o.end : start - o.start;
    }

    @Override
    public String toString() {
        if (this == EMPTY) return "[EMPTY]";
        return String.format("[%d..%d)", start, end);
    }

    public Range by(int step) {
        return new Range(start, end, step);
    }

    public Range toZero() {
        return offset(-start);
    }

    public Range offset(int offset) {
        return new Range(start + offset, end + offset);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int n = start;

            @Override
            public boolean hasNext() {
                return n < end;
            }

            @Override
            public Integer next() {
                int v = n;
                n += step;
                return v;
            }
        };
    }
}
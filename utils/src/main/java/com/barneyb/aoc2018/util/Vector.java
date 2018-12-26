package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Vector implements Comparable<Vector> {

    public static Vector parse(String input) {
        return scan(new Scanner(input.trim()));
    }

    public static Vector scan(Scanner s) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(s.readInt());
        while (s.probe(',')) q.enqueue(s.skip(',').readInt());
        return new Vector(q);
    }

    private final int[] dims;

    public Vector(int... dims) {
        this.dims = dims;
    }

    public Vector(Queue<Integer> dims) {
        int[] a = new int[dims.size()];
        Iterator<Integer> itr = dims.iterator();
        for (int i = 0; i < a.length; i++) {
            a[i] = itr.next();
        }
        this.dims = a;
    }

    public int dim(int d) {
        return dims[d];
    }

    public int dimensions() {
        return dims.length;
    }

    public int md(Vector o) {
        if (dimensions() != o.dimensions()) {
            throw new IllegalArgumentException("Mismatched dimensions: " + dimensions() + " vs " + o.dimensions());
        }
        int sum = 0;
        for (int i = 0, l = dimensions(); i < l; i++) {
            sum += Math.abs(dims[i] - o.dims[i]);
        }
        return sum;
    }

    @Override
    public int compareTo(Vector o) {
        if (dimensions() != o.dimensions()) {
            throw new IllegalArgumentException("Mismatched dimensions: " + dimensions() + " vs " + o.dimensions());
        }
        int c;
        for (int i = 0, l = dimensions(); i < l; i++) {
            c = dims[i] - o.dims[i];
            if (c != 0) return c;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector vector = (Vector) o;
        if (dims.length != vector.dims.length) return false;
        for (int i = dims.length - 1; i >= 0; i--) {
            if (dims[i] != vector.dims[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int n : dims) {
            result = result * 31 + n;
        }
        return result;
    }

}

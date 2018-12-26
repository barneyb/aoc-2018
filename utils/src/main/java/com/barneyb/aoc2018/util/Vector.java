package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Vector implements Comparable<Vector> {

    public static Vector parse(String input) {
        return scan(new Scanner(input.trim()));
    }

    private static Vector scan(Scanner s) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(s.readInt());
        while (s.probe(',')) q.enqueue(s.skip(',').readInt());
        return new Vector(q);
    }

    private final int[] ns;

    Vector(int... ns) {
        this.ns = ns;
    }

    Vector(Queue<Integer> ns) {
        int[] a = new int[ns.size()];
        Iterator<Integer> itr = ns.iterator();
        for (int i = 0; i < a.length; i++) {
            a[i] = itr.next();
        }
        this.ns = a;
    }

    public int i(int i) {
        return ns[i];
    }

    public int dimensions() {
        return ns.length;
    }

    public int md(Vector o) {
        if (dimensions() != o.dimensions()) {
            throw new IllegalArgumentException("Mismatched dimensions: " + dimensions() + " vs " + o.dimensions());
        }
        int sum = 0;
        for (int i = 0, l = dimensions(); i < l; i++) {
            sum += Math.abs(ns[i] - o.ns[i]);
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
            c = ns[i] - o.ns[i];
            if (c != 0) return c;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector vector = (Vector) o;
        if (ns.length != vector.ns.length) return false;
        for (int i = ns.length - 1; i >= 0; i--) {
            if (ns[i] != vector.ns[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int n : ns) {
            result = result * 31 + n;
        }
        return result;
    }

}

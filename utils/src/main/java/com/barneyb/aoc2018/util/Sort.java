package com.barneyb.aoc2018.util;

import java.util.Comparator;

public class Sort<T> {

    private final Comparator<T> comparator;

    public Sort() {
        comparator = (a, b) -> {
            if (a == null) return b == null ? 0 : 1;
            if (b == null) return -1;
            //noinspection unchecked
            return ((Comparable) a).compareTo(b);
        };
    }

    public Sort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sort(T[] a) {
        for (int i = 0, l = a.length - 1; i < l; i++) {
            int min = i;
            for (int j = i, k = a.length; j < k; j++) {
                if (comparator.compare(a[j], a[min]) < 0) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

//    private static void sort(Comparable[] a, int lo, int hi) {
//        if (hi <= lo) {
//            return;
//        }
//        int j = partition(a, lo, hi);
//        sort(a, lo, j - 1);
//        sort(a, j + 1, hi);
//    }
//
//    private static int partition(Comparable[] a, int lo, int hi) {
//        int j = lo;
//        Comparable v = a[j];
//        while (true) {
//
//        }
//    }

    private static void swap(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}

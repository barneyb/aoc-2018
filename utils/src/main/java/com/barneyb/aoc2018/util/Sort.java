package com.barneyb.aoc2018.util;

public class Sort {

    public static void sort(Comparable[] a) {
        for (int i = 0, l = a.length - 1; i < l; i++) {
            int min = i;
            for (int j = i, k = a.length; j < k; j++) {
                //noinspection unchecked
                if (a[j].compareTo(a[min]) < 0) {
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

    private static void swap(Comparable[]a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}

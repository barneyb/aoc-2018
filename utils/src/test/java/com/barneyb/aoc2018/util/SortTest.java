package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {

    @Test
    public void empty() {
        Integer[] a = new Integer[0];
        Sort.natural(a);
        assertArrayEquals(new Integer[0], a);
    }

    @Test
    public void integers() {
        Integer[] a = { 3, 5, 1, 4, 2 };
        Sort.natural(a);
        assertArrayEquals(new Integer[] {
                1, 2, 3, 4, 5
        }, a);
    }

    @Test
    public void dupes() {
        Integer[] a = { 2, 3, 1, 5, 2, 3, 4, 4, 3 };
        Sort.natural(a);
        assertArrayEquals(new Integer[] {
                1, 2, 2, 3, 3, 3, 4, 4, 5
        }, a);
    }

    @Test
    public void comparator() {
        Integer[] arr = { 3, 5, 1, 4, 2 };
        new Sort<>(Comparator.comparingInt((Integer a) -> a).reversed()).sort(arr);
        assertArrayEquals(new Integer[] {
                5, 4, 3, 2, 1
        }, arr);
    }

}
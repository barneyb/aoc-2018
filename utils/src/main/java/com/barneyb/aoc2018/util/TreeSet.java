package com.barneyb.aoc2018.util;

import java.util.Random;

public class TreeSet<E extends Comparable<E>> {

    private static Object VALUE = new int[0]; // this is serializable

    private BST<E, Object> tree;

    public TreeSet() {
        tree = new BST<>();
    }

    public void add(E element) {
        tree.put(element, VALUE);
    }

    public void clear() {
        tree.clear();
    }

    public int size() {
        return tree.size();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        @SuppressWarnings("unchecked")
        E element = (E) o;
        return tree.contains(element);
    }

    @SuppressWarnings("AssertWithSideEffects")
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        assert set.isEmpty();
        assert set.size() == 0;
        set.add(3);
        set.add(1);
        set.add(3);
        assert set.contains(1);
        assert ! set.contains(2);
        assert ! set.isEmpty();
        assert set.size() == 2;
        set.clear();
        assert set.isEmpty();
        assert set.size() == 0;
        if (args.length > 0) {
            perfTest(Integer.parseInt(args[0]));
        }
    }

    static void perfTest(int iterations) {
        TreeSet<Integer> set = new TreeSet<>();
        Random r = new Random();
        int range = 10 * iterations;
        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < iterations; i++) {
            set.add(r.nextInt(range));
        }
        for (int i = 0; i < 10 * iterations; i++) {
            set.contains(r.nextInt(range));
        }
        System.out.printf("Perf Test: %d ms%n", watch.elapsed());
    }

}

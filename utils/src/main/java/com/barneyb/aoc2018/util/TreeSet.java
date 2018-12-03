package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.Set;

import java.util.Iterator;
import java.util.Random;

public class TreeSet<E extends Comparable<E>> implements Set<E> {

    private static final Object VALUE = new int[0]; // this is serializable

    private final BST<E, Object> tree= new BST<>();

    public void add(E element) {
        tree.put(element, VALUE);
    }

    public void clear() {
        tree.clear();
    }

    public int size() {
        return tree.size();
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        @SuppressWarnings("unchecked")
        E element = (E) o;
        return tree.contains(element);
    }

    @Override
    public Iterator<E> iterator() {
        return tree.keys().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Iterator<E> itr = iterator(); itr.hasNext(); ) {
            E el = itr.next();
            sb.append(el);
            if (itr.hasNext()) {
                sb.append(',');
            }
        }
        return sb.append(']').toString();
    }

    public static void main(String[] args) {
        int iterations = 100_000; // ~550 ms
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

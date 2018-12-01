package com.barneyb.aoc2018.util;

import java.util.Random;

public class TreeSet<E extends Comparable<E>> {

    private class Node {
        E element;
        int size;
        Node left, right;

        Node(E element) {
            this(element, 1);
        }

        Node(E element, int size) {
            this.size = size;
            this.element = element;
        }

        void resetSize() {
            this.size = (left == null ? 0 : left.size)
                    + (right == null ? 0 : right.size)
                    + 1;
        }
    }

    private Node root;

    public TreeSet() {
    }

    public boolean add(E element) {
        if (element == null) throw new IllegalArgumentException("No nulls, yo");
        if (root == null) {
            root = new Node(element);
            return true;
        }
        return add(element, root);
    }

    private boolean add(E element, Node curr) {
        int cmp = element.compareTo(curr.element);
        boolean result = false; // fallthrough if hit
        if (cmp < 0) {
            if (curr.left == null) {
                curr.left = new Node(element);
                result = true;
            } else {
                result = add(element, curr.left);
            }
        } else if (cmp > 0) {
            if (curr.right == null) {
                curr.right = new Node(element);
                result = true;
            } else {
                result = add(element, curr.right);
            }
        }
        if (result) {
            curr.resetSize();
        }
        return result;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        return root == null ? 0 : root.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        @SuppressWarnings("unchecked")
        E element = (E) o;
        return contains(element, root);
    }

    private boolean contains(E element, Node curr) {
        if (curr == null) {
            return false; // miss
        }
        int cmp = element.compareTo(curr.element);
        if (cmp < 0) {
            return contains(element, curr.left);
        } else if (cmp > 0) {
            return contains(element, curr.right);
        } else {
            return true; // found it
        }
    }

    @SuppressWarnings("AssertWithSideEffects")
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        assert set.isEmpty();
        assert set.size() == 0;
        assert set.add(3);
        assert set.add(1);
        assert ! set.add(3);
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

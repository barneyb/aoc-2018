package com.barneyb.aoc2018.util;

@SuppressWarnings("WeakerAccess")
public class TreeSet<E extends Comparable<E>> {

    private class Node {
        E element;
        Node left;
        Node right;

        Node(E element) {
            this.element = element;
        }
    }

    private Node root;
    private int size = 0;

    public TreeSet() {
    }

    public boolean add(E element) {
        if (element == null) throw new IllegalArgumentException("No nulls, yo");
        if (root == null) {
            root = new Node(element);
            size += 1;
            return true;
        }
        Node curr = root;
        while (true) {
            if (element.compareTo(curr.element) < 0) {
                if (curr.left == null) {
                    curr.left = new Node(element);
                    size += 1;
                    return true;
                }
                curr = curr.left;
            } else if (element.compareTo(curr.element) > 0) {
                if (curr.right == null) {
                    curr.right = new Node(element);
                    size += 1;
                    return true;
                }
                curr = curr.right;
            } else {
                return false; // found it
            }
        }
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        @SuppressWarnings("unchecked")
        E element = (E) o;
        Node curr = root;
        while (curr != null) {
            if (element.compareTo(curr.element) < 0) {
                curr = curr.left;
            } else if (element.compareTo(curr.element) > 0) {
                curr = curr.right;
            } else {
                return true; // found it
            }
        }
        return false;
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
    }

}

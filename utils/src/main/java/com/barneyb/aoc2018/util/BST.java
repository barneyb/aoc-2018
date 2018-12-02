package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.ST;

public class BST<K extends Comparable<K>, V> implements ST<K, V> {

    private class Node {
        K key;
        V value;
        int size;
        Node left, right;

        Node(K key, V value) {
            this(key, value, 1);
        }

        Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    private Node root;

    public BST() {
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("No nulls, yo");
        }
        root = put(root, key, value);
    }

    private Node put(Node curr, K key, V value) {
        if (curr == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(curr.key);
        if (cmp < 0) {
            curr.left = put(curr.left, key, value);
        } else if (cmp > 0) {
            curr.right = put(curr.right, key, value);
        } else { // replace
            curr.value = value;
        }
        curr.size = size(curr.left) + size(curr.right) + 1;
        return curr;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V get(K key) {
        if (key == null) return null;
        return get(root, key);
    }

    private V get(Node curr, K key) {
        if (curr == null) {
            return null; // miss
        }
        int cmp = key.compareTo(curr.key);
        if (cmp < 0) {
            return get(curr.left, key);
        } else if (cmp > 0) {
            return get(curr.right, key);
        } else {
            return curr.value; // found it
        }
    }

}

package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.ST;

import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements ST<K, V> {

    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node {
        K key;
        V value;
        int size;
        Node left, right;
        boolean color = RED; // new nodes are always red-linked

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
        root.color = BLACK;
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
        if (isRed(curr.right) && ! isRed(curr.left)) {
            curr = rotateLeft(curr);
        }
        if (isRed(curr.left) && isRed(curr.left.left)) {
            curr = rotateRight(curr);
        }
        if (isRed(curr.left) && isRed(curr.right)) {
            flipColors(curr);
        }
        return curr;
    }

    private boolean isRed(Node n) {
        if (n == null) {
            // null links are black (by fiat)
            return false;
        }
        return n.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
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

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<K>();
        keys(root, q);
        return q;
    }

    private void keys(Node curr, Queue<K> q) {
        if (curr == null) return;
        keys(curr.left, q);
        q.enqueue(curr.key);
        keys(curr.right, q);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Iterator<K> itr = keys().iterator(); itr.hasNext(); ) {
            K k = itr.next();
            sb.append(k).append(':').append(get(k));
            if (itr.hasNext()) {
                sb.append(',');
            }
        }
        return sb.append('}').toString();
    }
}

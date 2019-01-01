package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.Collection;
import com.barneyb.aoc2018.api.ST;

import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements ST<K, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        final K key;
        V value;
        int size = 1; // new nodes always are just themselves
        Node left, right;
        boolean color = RED; // new nodes are always red-linked

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        void updateSize() {
            size = size(left) + size(right) + (value != null ? 1 : 0);
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
        curr.updateSize();
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

    @Override
    public void delete(K key) {
        // can't use the default w/ public put, because `null`
        if (contains(key)) {
            put(root, key, null);
        }
    }

    public void deleteMin() {
        // todo: this is also stupid
        delete(min());
    }

    public K min() {
        // todo: this is _stupid_
        return keys().iterator().next();
    }

    public void deleteMax() {
        // todo: this is also stupid
        delete(max());
    }

    public K max() {
        // todo: this is _stupid_
        K last = null;
        for (K k : keys()) {
            last = k;
        }
        return last;
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
        h.updateSize();
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.updateSize();
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

    public V get(K key, V defaultValue) {
        V v = get(key);
        return v == null ? defaultValue : v;
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
    public Collection<K> keys() {
        Queue<K> q = new Queue<>();
        keys(root, q);
        return q;
    }

    private void keys(Node curr, Queue<K> q) {
        if (curr == null) return;
        keys(curr.left, q);
        if (curr.value != null)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BST)) return false;
        //noinspection unchecked
        BST<K, V> bst = (BST<K, V>) o;
        if (size() != bst.size()) return false;
        Iterator<K> oki = bst.keys().iterator();
        for (K k : keys()) {
            K ok = oki.next();
            if (! k.equals(ok)) return false;
            if (! get(k).equals(bst.get(ok))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (K k : keys()) {
            result = 31 * result + k.hashCode();
            result = 31 * result + get(k).hashCode();
        }
        return result;
    }
}

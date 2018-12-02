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

        void resetSize() {
            this.size = (left == null ? 0 : left.size)
                    + (right == null ? 0 : right.size)
                    + 1;
        }
    }

    private Node root;

    public BST() {
    }

    @Override
    public void put(K key, V val) {
        if (key == null || val == null) {
            throw new IllegalArgumentException("No nulls, yo");
        }
        if (root == null) {
            root = new Node(key, val);
        }
        put(key, val, root);
    }

    private void put(K key, V value, Node curr) {
        int cmp = key.compareTo(curr.key);
        if (cmp < 0) {
            if (curr.left == null) {
                curr.left = new Node(key, value);
            } else {
                put(key, value, curr.left);
            }
            curr.resetSize();
        } else if (cmp > 0) {
            if (curr.right == null) {
                curr.right = new Node(key, value);
            } else {
                put(key, value, curr.right);
            }
            curr.resetSize();
        } else { // replace
            curr.value = value;
        }
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

    public V get(K key) {
        if (key == null) return null;
        return get(key, root);
    }

    private V get(K key, Node curr) {
        if (curr == null) {
            return null; // miss
        }
        int cmp = key.compareTo(curr.key);
        if (cmp < 0) {
            return get(key, curr.left);
        } else if (cmp > 0) {
            return get(key, curr.right);
        } else {
            return curr.value; // found it
        }
    }

    @SuppressWarnings("AssertWithSideEffects")
    public static void main(String[] args) {
        BST<Integer, Integer> st = new BST<>();
        assert st.isEmpty();
        assert st.size() == 0;
        st.put(3, 4);
        st.put(1, 2);
        st.put(3, 5);
        assert st.contains(1);
        assert ! st.contains(2);
        assert ! st.isEmpty();
        assert st.size() == 2;
        assert st.get(1) == 2;
        assert st.get(2) == null;
        assert st.get(3) == 5;
        st.clear();
        assert st.isEmpty();
        assert st.size() == 0;
    }

}

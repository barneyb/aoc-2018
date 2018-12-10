package com.barneyb.aoc2018.day08;

import java.util.Iterator;

public class Tree {

    private static class Node {
        Node[] children;
        int[] metadata;

        int getMetaSum() {
            int sum = 0;
            for (Node c : children) {
                sum += c.getMetaSum();
            }
            for (int n : metadata) {
                sum += n;
            }
            return sum;
        }

        int getValue() {
            if (children.length == 0) {
                return getMetaSum();
            }
            int v = 0;
            for (int i : metadata) {
                if (i <= children.length) {
                    v += children[i - 1].getValue(); // indexing basis!
                }
            }
            return v;
        }

    }

    private static class Digest implements Iterator<Integer> {
        private final int[] ns;
        private int i;

        public Digest(int[] ns, int i) {
            this.ns = ns;
            this.i = i;
        }

        @Override
        public boolean hasNext() {
            return i < ns.length;
        }

        @Override
        public Integer next() {
            return ns[i++];
        }
    }

    private Node root;

    Tree(int[] ns) {
        Digest d = new Digest(ns, 0);
        this.root = readNode(d);
        if (d.hasNext()) {
            throw new RuntimeException("Failed to consume all input");
        }
    }

    private Node readNode(Digest d) {
        Node n = new Node();
        int childCount = d.next();
        int metaCount = d.next();
        // eat the children
        n.children = new Node[childCount];
        for (int i = 0; i < childCount; i++) {
            n.children[i] = readNode(d);
        }
        n.metadata = new int[metaCount];
        for (int i = 0; i < metaCount; i++) {
            n.metadata[i] = d.next();
        }
        return n;
    }

    public int getMetaSum() {
        return root.getMetaSum();
    }

    public int getValue() {
        return root.getValue();
    }
}

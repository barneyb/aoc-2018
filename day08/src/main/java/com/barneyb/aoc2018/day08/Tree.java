package com.barneyb.aoc2018.day08;

import com.barneyb.aoc2018.util.Bag;
import com.barneyb.aoc2018.util.List;

import java.util.Iterator;

public class Tree {

    private static class Node {
        List<Node> children = new List<>();
        Bag<Integer> metadata = new Bag<>();

        void addChild(Node n) {
            children.add(n);
        }

        void addMeta(int n) {
            metadata.add(n);
        }

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
            if (children.isEmpty()) {
                return getMetaSum();
            }
            int v = 0;
            for (int i : metadata) {
                if (i <= children.size()) {
                    v += children.get(i - 1).getValue(); // indexing basis!
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
        this.root = readNode(new Digest(ns, 0));
    }

    private Node readNode(Digest d) {
        Node n = new Node();
        int childCount = d.next();
        int metaCount = d.next();
        // eat the children
        for (int j = 0; j < childCount; j++) {
            n.addChild(readNode(d));
        }
        for (int j = 0; j < metaCount; j++) {
            n.addMeta(d.next());
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

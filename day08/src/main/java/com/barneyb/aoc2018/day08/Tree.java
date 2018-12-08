package com.barneyb.aoc2018.day08;

public class Tree {

    private int metaSum;

    Tree(int[] ns) {
        doNode(ns, 0);
    }

    // do the node at i, recurse, and return the index after the node
    private int doNode(int[] ns, int i) {
        int childCount = ns[i++];
        int metaCount = ns[i++];
        // eat the children
        for (int j = 0; j < childCount; j++) {
            i = doNode(ns, i);
        }
        for (int j = 0; j < metaCount; j++) {
            metaSum += ns[i++];
        }
        return i;
    }

    public int getMetaSum() {
        return metaSum;
    }
}

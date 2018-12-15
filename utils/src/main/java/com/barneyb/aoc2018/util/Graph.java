package com.barneyb.aoc2018.util;

public class Graph {

    private final int siteCount;
    private int edgeCount = 0;
    private Bag<Integer>[] adjacentTo;

    public Graph(int siteCount) {
        this.siteCount = siteCount;
        //noinspection unchecked
        adjacentTo = (Bag<Integer>[]) new Bag[siteCount];
        for (int i = 0; i < adjacentTo.length; i++) {
            adjacentTo[i] = new Bag<>();
        }
    }

    public int getSiteCount() {
        return siteCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void addEdge(int from, int to) {
        adjacentTo[from].add(to);
        adjacentTo[to].add(from);
        edgeCount += 1;
    }

    public Bag<Integer> adjacentTo(int site) {
        return adjacentTo[site];
    }

}

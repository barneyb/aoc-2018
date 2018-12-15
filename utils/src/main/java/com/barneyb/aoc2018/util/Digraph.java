package com.barneyb.aoc2018.util;

public class Digraph {

    private final int siteCount;
    private int edgeCount = 0;
    private Bag<Integer>[] adjacentTo;
    private final int[] indegree;

    public Digraph(int siteCount) {
        this.siteCount = siteCount;
        //noinspection unchecked
        adjacentTo = (Bag<Integer>[]) new Bag[siteCount];
        indegree = new int[siteCount];
        for (int i = 0; i < adjacentTo.length; i++) {
            adjacentTo[i] = new Bag<>();
            indegree[i] = 0;
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
        edgeCount += 1;
        indegree[to] += 1;
    }

    public Bag<Integer> adjacentTo(int site) {
        return adjacentTo[site];
    }

    public int indegree(int site) {
        return indegree[site];
    }

}

package com.barneyb.aoc2018.util;

public class Graph {

    private final int siteCount;
    private int edgeCount = 0;
    private TreeSet<Integer>[] adjacentTo;

    public Graph(int siteCount) {
        this.siteCount = siteCount;
        //noinspection unchecked
        adjacentTo = (TreeSet<Integer>[]) new TreeSet[siteCount];
        for (int i = 0; i < adjacentTo.length; i++) {
            adjacentTo[i] = new TreeSet<>();
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

    public TreeSet<Integer> adjacentTo(int site) {
        return adjacentTo[site];
    }

    public boolean adjacent(int site1, int site2) {
        return adjacentTo[site1].contains(site2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < siteCount; i++) {
            sb.append(String.format("%4d: ", i));
            for (int j : adjacentTo[i]) {
                if (j < i) continue;
                sb.append(String.format("%4d, ", j));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}

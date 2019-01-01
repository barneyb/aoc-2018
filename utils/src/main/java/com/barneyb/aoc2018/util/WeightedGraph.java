package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.Collection;

public class WeightedGraph {

    private final int siteCount;
    private int edgeCount = 0;
    private BST<Integer, Integer>[] adjacentTo;

    public WeightedGraph(int siteCount) {
        this.siteCount = siteCount;
        //noinspection unchecked
        adjacentTo = (BST<Integer, Integer>[]) new BST[siteCount];
        for (int i = 0; i < adjacentTo.length; i++) {
            adjacentTo[i] = new BST<>();
        }
    }

    public int getSiteCount() {
        return siteCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void addEdge(int from, int to, int weight) {
        adjacentTo[from].put(to, weight);
        adjacentTo[to].put(from, weight);
        edgeCount += 1;
    }

    public Collection<Integer> adjacentTo(int site) {
        return adjacentTo[site].keys();
    }

    public int adjacentCount(int site) {
        return adjacentTo[site].size();
    }

    public boolean adjacent(int site1, int site2) {
        return adjacentTo[site1].contains(site2);
    }

    public int weight(int site1, int site2) {
        return adjacentTo[site1].get(site2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < siteCount; i++) {
            sb.append(String.format("%4d: ", i));
            for (int j : adjacentTo(i)) {
                if (j < i) continue;
                sb.append(String.format("%4d, ", j));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}

package com.barneyb.aoc2018.api;

public interface Graph {

    // Graph(int siteCount)

    int getSiteCount();

    int getEdgeCount();

    void addEdge(int from, int to);

    Iterable<Integer> adjacentTo(int site);

}

package com.barneyb.aoc2018.util;

public class UF {

    private int components;
    private final int[] sites;

    public UF(int siteCount) {
        this.components = siteCount;
        this.sites = new int[siteCount];
        for (int i = 0; i < siteCount; i++) {
            sites[i] = i;
        }
    }

    private int root(int n) {
        return sites[n];
    }

    public void union(int n, int m) {
        int rn = root(n);
        int rm = root(m);
        if (rn == rm) return;
        for (int i = 0; i < sites.length; i++) {
            if (sites[i] == rm) {
                sites[i] = rn;
            }
        }
        components -= 1;
    }

    public boolean connected(int n, int m) {
        return root(n) == root(m);
    }

    public int components() {
        return components;
    }

}

package com.barneyb.aoc2018.day23;

class ScaledPlot {

    static final int MAX_SIZE = 200;

    final Range xr, yr, sxr, syr;
    final int[][] grid;

    ScaledPlot(Range xRange, Range yRange) {
        xr = xRange;
        yr = yRange;
        sxr = scale(xr);
        syr = scale(yr);
        grid = new int[syr.size()][];
        for (int y = 0; y < grid.length; y++) {
            grid[y] = new int[sxr.size()];
        }
    }

    private Range scale(Range r) {
        return r.size() < MAX_SIZE ? r.toZero()
                : Range.halfOpen(0, MAX_SIZE);
    }

    public int incAndGet(int x, int y) {
        int sx = sxr.unscale(xr.scale(x));
        int sy = syr.unscale(yr.scale(y));
        return grid[sy][sx] += 1;
    }

    public int scaledGet(int x, int y) {
        return grid[y][x];
    }

    public int unscaleX(int x) {
        return xr.unscale(sxr.scale(x));
    }

    public int unscaleY(int y) {
        return yr.unscale(syr.scale(y));
    }
}

package com.barneyb.aoc2018.day23;

class ScaledPlot {

    static final int MAX_SIZE = 200;

    final Range xr, yr, sxr, syr;
    final int[][] grid;
    Range vRange = Range.inclusive(0, 0); // everything starts at zero

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

    public void inc(int x, int y) {
        int sx = sxr.unscale(xr.scale(x));
        int sy = syr.unscale(yr.scale(y));
        vRange = vRange.plus(grid[sy][sx] += 1);
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

    public double xFactor() {
        return 1.0 * sxr.size() / xr.size();
    }

    public double yFactor() {
        return 1.0 * syr.size() / yr.size();
    }

}

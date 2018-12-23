package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Point3D;

class Stats {
    Range xRange = Range.EMPTY;
    Range yRange = Range.EMPTY;
    Range zRange = Range.EMPTY;
    Range rRange = Range.EMPTY;

    Range pRange = Range.inclusive(0, 0);
    ScaledPlot pos_xy;
    ScaledPlot pos_yz;
    ScaledPlot pos_xz;

    Stats(Bot[] bots) {
        for (Bot b : bots) {
            Point3D p = b.pos;
            xRange = xRange.plus(p.x);
            yRange = yRange.plus(p.y);
            zRange = zRange.plus(p.z);
        }
        build(bots);
    }

    Stats(Bot[] bots, Range xRange, Range yRange, Range zRange) {
        this.xRange = xRange;
        this.yRange = yRange;
        this.zRange = zRange;
        build(bots);
    }

    private void build(Bot[] bots) {
        for (Bot b : bots) {
            rRange = rRange.plus(b.range);
        }
        pos_xy = new ScaledPlot(xRange, yRange);
        //noinspection SuspiciousNameCombination
        pos_yz = new ScaledPlot(yRange, zRange);
        pos_xz = new ScaledPlot(xRange, zRange);

        for (Bot b : bots) {
            Point3D p = b.pos;
            boolean inX = xRange.contains(p.x);
            boolean inY = yRange.contains(p.y);
            boolean inZ = zRange.contains(p.z);
            if (inX && inY) pRange = pRange.plus(pos_xy.incAndGet(p.x, p.y));
            if (inY && inZ) //noinspection SuspiciousNameCombination
                pRange = pRange.plus(pos_yz.incAndGet(p.y, p.z));
            if (inX && inZ) pRange = pRange.plus(pos_xz.incAndGet(p.x, p.z));
        }
    }

    private int[][] initGrid(int dg) {
        int[][] g = new int[dg][];
        for (int i = 0; i < dg; i++) {
            g[i] = new int[dg];
        }
        return g;
    }

}

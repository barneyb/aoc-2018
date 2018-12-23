package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Point3D;

class Stats {
    Range xRange = Range.EMPTY;
    Range yRange = Range.EMPTY;
    Range zRange = Range.EMPTY;
    Range rRange = Range.EMPTY;
    Range gRange;

    Range pRange = Range.inclusive(0, 0);
    int[][] pos_xy;
    int[][] pos_yz;
    int[][] pos_xz;

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
        gRange = Range.halfOpen(0,
                Math.min(Math.max(Math.max(xRange.size(), yRange.size()), zRange.size()), 200));
        int dg = gRange.end();
        pos_xy = initGrid(dg);
        pos_yz = initGrid(dg);
        pos_xz = initGrid(dg);

        for (Bot b : bots) {
            Point3D p = b.pos;
            int cx = gRange.unscale(xRange.scale(p.x));
            int cy = gRange.unscale(yRange.scale(p.y));
            int cz = gRange.unscale(zRange.scale(p.z));
            pRange = pRange
                    .plus(pos_xy[cy][cx] += 1)
                    .plus(pos_yz[cz][cy] += 1)
                    .plus(pos_xz[cz][cx] += 1);
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

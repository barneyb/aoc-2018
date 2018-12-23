package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Point3D;

class Stats {
    Range xRange = Range.EMPTY;
    Range yRange = Range.EMPTY;
    Range zRange = Range.EMPTY;
    Range rRange = Range.EMPTY;
    Range gRange;

    Range pRange = new Range(0, 1);
    int[][] pos_xy;
    int[][] pos_yz;
    int[][] pos_xz;

    Stats(Bot[] bots) {
        for (Bot b : bots) {
            Point3D p = b.pos;
            xRange = xRange.plus(p.x);
            yRange = yRange.plus(p.y);
            zRange = zRange.plus(p.z);
            rRange = rRange.plus(b.range);
        }

        gRange = new Range(0,
                Math.min(Math.max(Math.max(xRange.size(), yRange.size()), zRange.size()), 200));
        int dg = gRange.end();
        pos_xy = new int[dg][];
        pos_yz = new int[dg][];
        pos_xz = new int[dg][];

        for (int i = 0; i < dg; i++) {
            pos_xy[i] = new int[dg];
            pos_yz[i] = new int[dg];
            pos_xz[i] = new int[dg];
        }

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
}

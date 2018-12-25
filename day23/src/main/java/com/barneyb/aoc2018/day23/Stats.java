package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Point3D;

import java.io.PrintWriter;
import java.io.StringWriter;

class Stats {
    Range xRange = Range.EMPTY;
    Range yRange = Range.EMPTY;
    Range zRange = Range.EMPTY;
    Range rRange = Range.EMPTY;

//    ScaledPlot pos_xy;
//    ScaledPlot pos_yz;
//    ScaledPlot pos_xz;

//    ScaledPlot heat_xy;
    ScaledPlot heat_yz;
//    ScaledPlot heat_xz;

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
        Axis ya = new Axis("y", yRange);
        Axis xa = new Axis("x", xRange);
        Axis za = new Axis("z", zRange);
//        pos_xy = new ScaledPlot(xa, ya);
//        pos_yz = new ScaledPlot(ya, za);
//        pos_xz = new ScaledPlot(xa, za);
//
//        for (Bot b : bots) {
//            Point3D p = b.pos;
//            boolean inX = xRange.contains(p.x);
//            boolean inY = yRange.contains(p.y);
//            boolean inZ = zRange.contains(p.z);
//            if (inX && inY) pos_xy.inc(p.x, p.y);
//            if (inY && inZ) //noinspection SuspiciousNameCombination
//                pos_yz.inc(p.y, p.z);
//            if (inX && inZ) pos_xz.inc(p.x, p.z);
//        }

//        heat_xy = new ScaledPlot(xa, ya);
        heat_yz = new ScaledPlot(ya, za);
//        heat_xz = new ScaledPlot(xa, za);

        for (Bot b : bots) {
            int r = b.range;
            Point3D p = b.pos;
//            addToHeatmap(heat_xy, new Point(p.x, p.y), r);
            //noinspection SuspiciousNameCombination
            addToHeatmap(heat_yz, new Point(p.y, p.y), r);
//            addToHeatmap(heat_xz, new Point(p.x, p.z), r);
        }
    }

    private void addToHeatmap(ScaledPlot plot, Point p, int r) {
        int dx = (int) (1 / plot.xFactor());
        int dy = (int) (1 / plot.yFactor());
        for (int x = p.x - r, mx = p.x; x <= mx; x += dx) {
            if (! plot.xAxis.range.contains(x)) continue;
            int spent = r - Math.abs(p.x - x);
            for (int y = p.y - r + spent, my = p.y + r - spent; y <= my; y += dy) {
                if (! plot.yAxis.range.contains(y)) continue;
                plot.inc(x, y);
            }
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        out.printf("   %12s %12s %12s%n", "min", "max", "range");
        out.printf("x  %,12d %,12d %,12d%n", xRange.start(), xRange.end() - 1, xRange.size());
        out.printf("y  %,12d %,12d %,12d%n", yRange.start(), yRange.end() - 1, yRange.size());
        out.printf("z  %,12d %,12d %,12d%n", zRange.start(), zRange.end() - 1, zRange.size());
        out.printf("r  %,12d %,12d %,12d%n", rRange.start(), rRange.end() - 1, rRange.size());
        return sw.toString();
    }
}

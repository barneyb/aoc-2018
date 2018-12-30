package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Point3D;
import com.barneyb.aoc2018.util.Scanner;
import com.barneyb.aoc2018.util.Vector;

class Bot {

    static Bot parse(String input) {
        Scanner s = new Scanner(input);
        return new Bot(
                Point3D.scan(s.skip("pos=<")),
                s.skip(">, r=").readInt()
        );
    }

    final Point3D pos;
    final int range;

    Bot(Point3D pos, int range) {
        this.pos = pos;
        this.range = range;
    }

    boolean inRange(Bot b) {
        return inRange(b.pos);
    }

    boolean inRange(Vector p) {
        return pos.md(p) <= range;
    }

    @Override
    public String toString() {
        return String.format("pos=<%d,%d,%d>, r=%d", pos.x(), pos.y(), pos.z(), range);
    }

    public int md(Bot b) {
        return pos.md(b.pos);
    }

    public boolean overlaps(Bot b) {
        return md(b) <= (range + b.range);
    }

    public int overlapsBy(Bot b) {
        return (range + b.range) - md(b);
    }

    public Point3D boundaryTowards(Bot b) {
        return boundaryTowards(b.pos);
    }

    public Point3D boundaryTowards(Point3D p) {
        int dx = p.x() - pos.x();
        int dy = p.y() - pos.y();
        int dz = p.z() - pos.z();
        int md = p.md(pos);
        return new Point3D(
                p.x() + (int) (range * (1.0 * dx / md)),
                p.y() + (int) (range * (1.0 * dy / md)),
                p.z() + (int) (range * (1.0 * dz / md))
        );
    }

    public Vector midpoint(Bot o) {
        Point3D a = this.boundaryTowards(o);
        Point3D b = o.boundaryTowards(this);
        return a.midpoint(b);
    }

}

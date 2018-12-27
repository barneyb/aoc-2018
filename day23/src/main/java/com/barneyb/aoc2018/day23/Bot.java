package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Bag;
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

    public Bag<Point3D> overlap(Bot b) {
        Bag<Point3D> overlap = new Bag<>();

        return overlap;
    }
}

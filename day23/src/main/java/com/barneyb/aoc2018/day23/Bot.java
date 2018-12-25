package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Point3D;
import com.barneyb.aoc2018.util.Scanner;

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

    boolean inRange(Point3D p) {
        return pos.md(p) <= range;
    }

    @Override
    public String toString() {
        return String.format("pos=<%d,%d,%d>, r=%d", pos.x, pos.y, pos.z, range);
    }

}

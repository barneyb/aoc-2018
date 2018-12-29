package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Bag;
import com.barneyb.aoc2018.util.Point3D;
import com.barneyb.aoc2018.util.Vector;

class Swarm {

    static Swarm parse(String input) {
        String[] lines = input.trim().split("\n");
        Bot[] bots = new Bot[lines.length];
        for (int i = 0; i < lines.length; i++) {
            bots[i] = Bot.parse(lines[i]);
        }
        return new Swarm(bots);
    }

    final Bot[] bots;
    private final Bot strongest;
    private final Point3D min;
    private final Point3D max;
    private final Point3D center;

    public Swarm(Bot[] bots) {
        this.bots = bots;
        Bot s = bots[0];
        int minx = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxy = Integer.MIN_VALUE;
        int minz = Integer.MAX_VALUE;
        int maxz = Integer.MIN_VALUE;
        for (Bot b : bots) {
            if (b.range > s.range) s = b;
            minx = Math.min(minx, b.pos.x());
            maxx = Math.max(maxx, b.pos.x());
            miny = Math.min(miny, b.pos.y());
            maxy = Math.max(maxy, b.pos.y());
            minz = Math.min(minz, b.pos.z());
            maxz = Math.max(maxz, b.pos.z());
        }
        this.strongest = s;
        min = new Point3D(minx, miny, minz);
        max = new Point3D(maxx, maxy, maxz);
        center = new Point3D(
                (min.x() + max.x()) / 2,
                (min.y() + max.y()) / 2,
                (min.z() + max.z()) / 2);
    }

    Bot strongest() {
        return strongest;
    }

    Point3D min() {
        return min;
    }

    Point3D max() {
        return max;
    }

    Point3D center() {
        return center;
    }

    Bag<Bot> canSignal(Vector p) {
        Bag<Bot> bs = new Bag<>();
        for (Bot b : bots) {
            if (b.inRange(p)) bs.add(b);
        }
        return bs;
    }

    public int botCount() {
        return bots.length;
    }
}

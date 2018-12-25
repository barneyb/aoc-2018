package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Bag;
import com.barneyb.aoc2018.util.Point3D;
import com.barneyb.aoc2018.util.Stopwatch;
import com.barneyb.aoc2018.util.TreeSet;

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

    public Swarm(Bot[] bots) {
        this.bots = bots;
    }

    Bot strongest() {
        Bot s = bots[0];
        for (Bot b : bots) {
            if (b.range > s.range) s = b;
        }
        return s;
    }

    // includes self!
    Bag<Bot> inRangeOf(Bot bot) {
        Bag<Bot> bs = new Bag<>();
        for (Bot b : bots) {
            if (bot.inRange(b)) bs.add(b);
        }
        return bs;
    }

    Point3D max(Range rx, Range ry, Range rz) {
        TreeSet<Point3D> ps = new TreeSet<>();
        int max = Integer.MIN_VALUE;
        Stopwatch watch = new Stopwatch();
        for (int x = rx.start(), mx = rx.end(); x < mx; x++) {
            for (int y = ry.start(), my = ry.end(); y < my; y++) {
                for (int z = rz.start(), mz = rz.end(); z < mz; z++) {
                    Point3D p = new Point3D(x, y, z);
                    int count = 0;
                    for (int i = bots.length - 1; i >= 0; i--) {
                        if (bots[i].inRange(p)) count++;
                        if (i + count < max) break;
                    }
                    if (count > max) {
                        max = count;
                        ps.clear();
                    }
                    if (count == max) {
                        ps.add(p);
                    }
                }
                if (y % 20 == 0) {
                    int steps = Math.abs(ry.start() - y);
                    if (steps == 0) continue;
                    long elapsed = watch.elapsed();
                    long expected = elapsed * ry.size() / steps;
                    long remaining = expected - elapsed;
                    System.out.printf("%d of %d in %d ms; %d sec total, %d sec to go%n", steps, ry.size(), elapsed, expected / 1000, remaining / 1000);
                }

            }
        }
        System.out.println(ps.size());
        return ps.min();
    }

}

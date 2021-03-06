package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Bounds;
import com.barneyb.aoc2018.util.Point;

public class Pattern {

    private final Light[] lights;
    private Light sentinel;
    private Point sentinelStart;

    public Pattern(Light[] lights) {
        this.lights = lights;
        for (Light l : lights) {
            if (l.vel.x != 0) {
                sentinel = l;
                sentinelStart = l.pos;
                break;
            }
        }
    }

    public void tick(int steps) {
        for (Light l : lights) {
            l.tick(steps);
        }
    }

    public Bounds bounds() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Light l : lights) {
            minX = Math.min(minX, l.pos.x);
            minY = Math.min(minY, l.pos.y);
            maxX = Math.max(maxX, l.pos.x);
            maxY = Math.max(maxY, l.pos.y);
        }
        return new Bounds(
                new Point(minX, minY),
                new Point(maxX, maxY)
        );
    }

    public int tickCount() {
        return (sentinel.pos.x - sentinelStart.x) / sentinel.vel.x;
    }

    @Override
    public String toString() {
        Bounds b = bounds();
        int w = (int) b.width();
        int h = (int) b.height();
        if (w > 3000 || h > 3000) return "too big, sorry " + b;
        boolean[][] grid = new boolean[h][];
        for (int y = 0; y < h; y++) {
            grid[y] = new boolean[w];
        }
        for (Light l : lights) {
            Point p = l.pos.minus(b.min());
            grid[p.y][p.x] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < h; y++) {
            if (y > 0) sb.append('\n');
            for (int x = 0; x < w; x++) {
                sb.append(grid[y][x] ? '#' : '.');
            }
        }
        return sb.toString();
    }

}

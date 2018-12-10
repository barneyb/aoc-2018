package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Point;

public class Array {

    private final Particle[] particles;
    private int tickCount = 0;

    public Array(Particle[] particles) {
        this.particles = particles;
    }

    public void tick() {
        for (Particle p : particles) {
            p.tick();
        }
        tickCount += 1;
    }

    public void untick() {
        for (Particle p : particles) {
            p.untick();
        }
        tickCount -= 1;
    }

    public Bounds bounds() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Particle p : particles) {
            minX = Math.min(minX, p.pos.x);
            minY = Math.min(minY, p.pos.y);
            maxX = Math.max(maxX, p.pos.x);
            maxY = Math.max(maxY, p.pos.y);
        }
        return new Bounds(
                new Point(minX, minY),
                new Point(maxX, maxY)
        );
    }

    public int tickCount() {
        return tickCount;
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
        for (Particle particle : particles) {
            Point p = particle.pos.minus(b.min);
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

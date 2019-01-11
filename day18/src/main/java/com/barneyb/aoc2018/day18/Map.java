package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.Histogram;
import com.barneyb.aoc2018.util.Point;

class Map {

    public static Map parse(String input) {
        String[] lines = input.trim().split("\n");
        char[][] grid = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }
        return new Map(grid);
    }

    static final char OPEN = '.';
    static final char TREES = '|';
    static final char LUMBERYARD = '#';

    private char[][] grid;
    private final int width, height;
    private int tickCount = 0;

    public Map(char[][] grid) {
        this.grid = grid;
        this.width = grid[0].length;
        this.height = grid.length;
    }

    public char curr(Point p) {
        return curr(p.x, p.y);
    }

    public char curr(int x, int y) {
        return grid[y][x];
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public void tick() {
        char[][] next = new char[height][];
        for (int y = 0; y < height; y++) {
            char[] row = next[y] = new char[width];
            for (int x = 0; x < width; x++) {
                row[x] = next(new Point(x, y));
            }
        }
        grid = next;
        tickCount += 1;
    }

    public void tick(int steps) {
        for (int i = 0; i < steps; i++) {
            tick();
        }
    }

    public char next(Point p) {
        char c = curr(p);
        Histogram<Character> h = buildHist(p);
        if (c == OPEN) {
            return h.get(TREES) >= 3 ? TREES : OPEN;
        } else if (c == TREES) {
            return h.get(LUMBERYARD) >= 3 ? LUMBERYARD : TREES;
        } else { // LUMBERYARD
            return h.get(LUMBERYARD) >= 1 && h.get(TREES) >= 1 ? LUMBERYARD : OPEN;
        }
    }

    public Histogram<Character> buildHist(Point p) {
        Histogram<Character> h = new Histogram<>();
        for (int y = p.y - 1; y <= p.y + 1; y++) {
            if (y < 0) continue;
            if (y >= height) continue;
            for (int x = p.x - 1; x <= p.x + 1; x++) {
                if (x < 0) continue;
                if (x >= width) continue;
                if (x == p.x && y == p.y) continue;
                h.count(curr(x, y));
            }
        }
        return h;
    }

    public int resourceValue() {
        int treeCount = 0;
        int yardCount = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (curr(x, y) == TREES) treeCount += 1;
                if (curr(x, y) == LUMBERYARD) yardCount += 1;
            }
        }
        return treeCount * yardCount;
    }

    public int tickCount() {
        return tickCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(height * width + height - 1);
        for (int y = 0; y < height; y++) {
            if (y > 0) sb.append('\n');
            sb.append(grid[y]);
        }
        return sb.toString();
    }

}

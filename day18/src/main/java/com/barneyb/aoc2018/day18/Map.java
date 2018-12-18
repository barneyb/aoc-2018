package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.Histogram;
import com.barneyb.aoc2018.util.Point;

public class Map {

    static Map parse(String input) {
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

    Map(char[][] grid) {
        this.grid = grid;
        this.width = grid[0].length;
        this.height = grid.length;
    }

    void tick() {
        char[][] next = new char[height][];
        for (int y = 0; y < height; y++) {
            char[] row = next[y] = new char[width];
            for (int x = 0; x < width; x++) {
                row[x] = next(new Point(x, y));
            }
        }
        grid = next;
    }

    void tick(int steps) {
        for (int i = 0; i < steps; i++) {
            tick();
        }
    }

    char next(Point p) {
        char c = grid[p.y][p.x];
        Histogram<Character> h = buildHist(p);
        if (c == OPEN) {
            return h.get(TREES) >= 3 ? TREES : OPEN;
        } else if (c == TREES) {
            return h.get(LUMBERYARD) >= 3 ? LUMBERYARD : TREES;
        } else { // LUMBERYARD
            return h.get(LUMBERYARD) >= 1 && h.get(TREES) >= 1 ? LUMBERYARD : OPEN;
        }
    }

    Histogram<Character> buildHist(Point p) {
        Histogram<Character> h = new Histogram<>();
        for (int y = p.y - 1; y <= p.y + 1; y++) {
            if (y < 0) continue;
            if (y >= height) continue;
            for (int x = p.x - 1; x <= p.x + 1; x++) {
                if (x < 0) continue;
                if (x >= width) continue;
                if (x == p.x && y == p.y) continue;
                h.count(grid[y][x]);
            }
        }
        return h;
    }

    public int resourceValue() {
        int treeCount = 0;
        int yardCount = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == TREES) treeCount += 1;
                if (grid[y][x] == LUMBERYARD) yardCount += 1;
            }
        }
        return treeCount * yardCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            if (y > 0) sb.append('\n');
            sb.append(grid[y]);
        }
        return sb.toString();
    }

}

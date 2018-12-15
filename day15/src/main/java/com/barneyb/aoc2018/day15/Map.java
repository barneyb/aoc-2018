package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Queue;

class Map {

    static final char SPACE = '.';
    static final char WALL = '#';
    static final char GOBLIN = 'G';
    static final char ELF = 'E';

    static Map parse(String input) {
        String[] lines = input.trim().split("\n");
        int height = lines.length;
        int width = lines[0].length();
        char[][] grid = new char[height][];
        int goblinCount = 0;
        int elfCount = 0;
        BST<Character, Unit> units = new BST<>();
        for (int y = 0; y < height; y++) {
            char[] row = grid[y] = lines[y].toCharArray();
            for (int x = 0; x < width; x++) {
                char c = row[x];
                char l;
                if (c == GOBLIN) {
                    l = (char) ('a' + goblinCount++);
                } else if (c == ELF) {
                    l = (char) ('A' + elfCount++);
                } else {
                    continue;
                }
                units.put(l, new Unit(l));
                row[x] = l;
            }
        }
        return new Map(width, height, grid, units);
    }

    private final int height;
    private final int width;
    private final char[][] grid;
    private final BST<Character, Unit> units;

    Map(int width, int height, char[][] grid, BST<Character, Unit> units) {
        this.height = height;
        this.width = width;
        this.grid = grid;
        this.units = units;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Queue<Unit> us = new Queue<>();
        for (int y = 0; y < height; y++) {
            if (y > 0) sb.append('\n');
            sb.append(grid[y]);
            for (int x = 0; x < width; x++) {
                char c = grid[y][x];
                if (c != SPACE && c != WALL) {
                    us.enqueue(units.get(c));
                }
            }
            while (! us.isEmpty()) {
                Unit u = us.dequeue();
                sb.append(' ')
                        .append(u.label())
                        .append('(')
                        .append(u.hitPoints())
                        .append(')');
            }
        }
        return sb.toString();
    }

}

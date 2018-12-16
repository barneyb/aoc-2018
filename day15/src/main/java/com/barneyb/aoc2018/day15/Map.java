package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Queue;
import com.barneyb.aoc2018.util.TreeSet;

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
                    l = (char) ('A' + goblinCount++);
                } else if (c == ELF) {
                    l = (char) ('a' + elfCount++);
                } else {
                    continue;
                }
                units.put(l, new Unit(l, new Point(x, y)));
                row[x] = l;
            }
        }
        return new Map(width, height, grid, units);
    }

    private final int height;
    private final int width;
    private final char[][] grid;
    private final BST<Character, Unit> units;
    private boolean elfKilled;
    private boolean noDeadElves;

    Map(int width, int height, char[][] grid, BST<Character, Unit> units) {
        this.height = height;
        this.width = width;
        this.grid = grid;
        this.units = units;
    }

    void armElves(int attack) {
        for (Character c : this.units.keys()) {
            Unit u = this.units.get(c);
            if (u.isElf()) u.updateArmament(attack);
        }
    }

    void noDeadElves() {
        noDeadElves = true;
    }

    int[][] gridToPaint() {
        int[][] g = new int[grid.length][];
        for (int y = 0; y < grid.length; y++) {
            int[] dest = g[y] = new int[grid[y].length];
            for (int x = 0; x < dest.length; x++) {
                dest[x] = 0;
            }
        }
        return g;
    }

    boolean isOpen(Point p) {
        return grid[p.y][p.x] == SPACE;
    }

    boolean isOver() {
        if (noDeadElves && elfKilled) return true;
        boolean foundGoblin = false;
        boolean foundElf = false;
        for (Unit u : survivors()) {
            if (u.isGoblin()) {
                if (foundElf) return false;
                foundGoblin = true;
            } else {
                if (foundGoblin) return false;
                foundElf = true;
            }
        }
        return true;
    }

    Iterable<Unit> survivors() {
        TreeSet<Unit> units = new TreeSet<>();
        for (Character c : this.units.keys()) {
            Unit u = this.units.get(c);
            if (u.alive()) units.add(u);
        }
        return units;
    }

    Iterable<Unit> casualties() {
        TreeSet<Unit> units = new TreeSet<>();
        for (Character c : this.units.keys()) {
            Unit u = this.units.get(c);
            if (! u.alive()) units.add(u);
        }
        return units;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    String toString(boolean withStats) {
        StringBuilder sb = new StringBuilder();
        Queue<Unit> us = new Queue<>();
        for (int y = 0; y < height; y++) {
            if (y > 0) sb.append('\n');
            sb.append(grid[y]);
            for (int x = 0; x < width; x++) {
                char c = grid[y][x];
                if (withStats && c != SPACE && c != WALL) {
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

    void move(Unit u, Point p) {
        assert isOpen(p);
        Point l = u.location();
        assert p.adjacent(l);
        grid[l.y][l.x] = SPACE;
        grid[p.y][p.x] = u.label();
        u.update(p);
    }

    void attack(Unit attacker, Unit victim) {
        victim.defend(attacker);
        if (! victim.alive()) {
            if (victim.isElf()) elfKilled = true;
            Point l = victim.location();
            grid[l.y][l.x] = SPACE;
        }
    }

}

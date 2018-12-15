package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Graph;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.TreeSet;

class Map {

    public static final char WALL = '#';
    public static final char SPACE = '.';
    public static final char ELF = 'E';
    public static final char GOBLIN = 'G';

    static Map parse(String input) {
        String[] lines = input.trim().split("\n");
        int width = lines[0].length();
        int height = lines.length;
        Map map = new Map(width, height);
        for (int y = 0; y < height; y++) {
            char[] row = lines[y].toCharArray();
            for (int x = 0; x < width; x++) {
                char c = row[x];
                if (c == WALL) continue;
                if (x > 0 && row[x - 1] != WALL) {
                    map.addAdjacency(new Point(x, y), new Point(x - 1, y));
                }
                if (y > 0 && lines[y - 1].charAt(x) != WALL) {
                    map.addAdjacency(new Point(x, y), new Point(x, y - 1));
                }
                if (c == ELF || c == GOBLIN) {
                    map.addUnit(c, new Point(x, y));
                }
            }
        }
        return map;
    }

    private final int width, height;
    private final Graph grid;
    private TreeSet<Unit> units = new TreeSet<>();
    private int elfCount, goblinCount;

    Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Graph(height * width);
    }

    private void addAdjacency(Point p, Point q) {
        grid.addEdge(p.index(width), q.index(width));
    }

    private void addUnit(char type, Point point) {
        char label = (char) (type == ELF
                ? ('A' + elfCount++)
                : ('a' + goblinCount++));
        units.add(new Unit(label, point));
    }

    TreeSet<Unit> units() {
        return units;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean labelUnits) {
        char[][] lines = new char[height][];
        for (int y = 0; y < height; y++) {
            char[] sb = lines[y] = new char[width];
            for (int x = 0; x < width; x++) {
                Point p = new Point(x, y);
                int i = p.index(width);
                sb[x] = grid.adjacentTo(i).size() == 0 ? WALL : SPACE;
            }
        }
        for (Unit u : units) {
            Point p = u.at();
            lines[p.y][p.x] = labelUnits
                    ? u.label()
                    : u.isElf() ? ELF : GOBLIN;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i > 0) sb.append('\n');
            sb.append(lines[i]);
        }
        return sb.toString();
    }
}

package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Bounds;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.TreeSet;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

class Earth {

    static Earth parse(String input) {
        String[] lines = input.trim().split("\n");
        TreeSet<Point> clays = new TreeSet<>();
        Point spring = null;
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == SPRING) {
                    spring = new Point(x, y);
                } else if (c == CLAY) {
                    clays.add(new Point(x, y));
                }
            }
        }
        if (spring == null) {
            throw new IllegalArgumentException("The spring's location must be provided (w/ '+')");
        }
        return new Earth(clays, spring);
    }

    static final char CLAY = '#';
    static final char SAND = '.';
    static final char SPRING = '+';
    static final char RUNNING = '|';
    static final char POOL = '~';

    private static final int UNBOUNDED = 9999999;

    private final BST<Point, Character> scan;
    private final Point spring;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private BiConsumer<Point, Character> observer;

    Earth(Vein[] clays) {
        this(clays, new Point(500, 0));
    }

    Earth(Vein[] clays, Point spring) {
        this.scan = new BST<>();
        this.spring = spring;
        scan.put(spring, SPRING);
        Bounds b = null;
        for (Vein vein : clays) {
            b = b == null ? vein.bounds() : b.plus(vein.bounds());
            for (Point p : vein.points()) {
                scan.put(p, CLAY);
            }
        }
        Bounds bounds = b;
        assert bounds != null;
        this.minX = bounds.min().x - 1;
        this.maxX = bounds.max().x + 1;
        this.minY = bounds.min().y;
        this.maxY = bounds.max().y;
    }

    Earth(TreeSet<Point> clays, Point spring) {
        this.scan = new BST<>();
        this.spring = spring;
        scan.put(spring, SPRING);
        Bounds b = null;
        for (Point p : clays) {
            b = b == null ? new Bounds(p, p) : b.plus(p);
            scan.put(p, CLAY);
        }
        Bounds bounds = b;
        assert bounds != null;
        this.minX = bounds.min().x - 1;
        this.maxX = bounds.max().x + 1;
        this.minY = bounds.min().y;
        this.maxY = bounds.max().y;
    }

    void runWater() {
        runWater(new Point(spring.x, spring.y + 1));
    }

    private char get(Point p) {
        return scan.get(p, SAND);
    }

    private void set(Point p, char c) {
        if (scan.contains(p)) {
            if (scan.get(p) == c) return;
            if (scan.get(p) != RUNNING || c != POOL)
                throw new IllegalArgumentException(p + " is already '" + scan.get(p) + "'");
        }
        scan.put(p, c);
        if (observer != null) observer.accept(p, c);
    }

    private void runWater(Point src) {
        char c = get(src);
        if (c != SAND) {
            // nothing to do
            return;
        }
        int level = src.y;
        while (level <= maxY) {
            Point p = new Point(src.x, level);
            c = get(p);
            if (c == SAND) {
                // fall some more
                set(p, RUNNING);
                level += 1;
            } else if (c == RUNNING) {
                // already did this part
                return;
            } else if (c == POOL || c == CLAY) {
                flood(new Point(src.x, level - 1));
                return;
            }
        }
    }

    private void flood(Point start) {
        int left = search(start, -1);
        int right = search(start, 1);
        if (left == UNBOUNDED || right == UNBOUNDED) return;
        for (int x = left; x <= right; x++) {
            set(new Point(x, start.y), POOL);
        }
        flood(new Point(start.x, start.y - 1));
    }

    private int search(Point start, int dx) {
        if (get(start) == SAND) set(start, RUNNING);
        int x = start.x + dx;
        while (true) {
            Point p = new Point(x, start.y);
            char c = get(p);
            if (c == SAND || c == RUNNING) {
                if (c == RUNNING) {
                    if (get(new Point(x, start.y + 1)) == RUNNING) {
                        return UNBOUNDED;
                    }
                } else {
                    set(p, RUNNING);
                }
                Point q = new Point(x, start.y + 1);
                if (get(q) == SAND) {
                    runWater(q);
                    return UNBOUNDED;
                } else {
                    x += dx;
                }
            } else {
                return x - dx;
            }
        }
    }

    int wetTiles() {
        return countTiles(c -> c == RUNNING || c == POOL);
    }

    int resTiles() {
        return countTiles(c -> c == POOL);
    }

    private int countTiles(Predicate<Character> test) {
        int n = 0;
        for (Point p : scan.keys()) {
            if (p.y < minY) continue;
            char c = scan.get(p);
            if (test.test(c)) {
                n += 1;
            }
        }
        return n;
    }

    @Override
    public String toString() {
        return toString(0, maxY, false);
    }

    String toString(boolean coords) {
        return toString(0, maxY, coords);
    }

    String toString(int minY, int maxY, boolean coords) {
        StringBuilder sb = new StringBuilder();
        if (coords) {
            if (maxX >= 100) {
                for (int x = minX; x <= maxX; x++) {
                    int i = x / 100;
                    if (i == 0) sb.append(' ');
                    else sb.append(i);
                }
                sb.append('\n');
            }
            for (int x = minX; x <= maxX; x++) {
                int i = x % 100 / 10;
                if (i == 0) sb.append(maxX >= 100 ? '0' : ' ');
                else sb.append(i);
            }
            sb.append('\n');
            for (int x = minX; x <= maxX; x++) {
                sb.append(x % 10);
            }
            sb.append('\n');
        }
        for (int y = minY; y <= maxY; y++) {
            if (y > minY) sb.append('\n');
            for (int x = minX; x <= maxX; x++) {
                Point p = new Point(x, y);
                Character c = scan.get(p);
                sb.append(c == null ? p.equals(spring) ? SPRING : SAND : c);
            }
            if (coords) {
                sb.append(' ').append(y);
            }
        }
        return sb.toString();
    }

    public void setObserver(BiConsumer<Point, Character> observer) {
        this.observer = observer;
    }

    int height() {
        return maxY - minY;
    }

    int width() {
        return maxX - minX;
    }

}

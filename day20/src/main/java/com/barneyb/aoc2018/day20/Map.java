package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.*;

import static com.barneyb.aoc2018.util.Dir.*;

class Map {

    static Map parse(String input) {
        input = input.trim();
        return new Map(new CharDigest(input.substring(1, input.length() - 1).toCharArray()));
    }

    private final TreeSet<Point> doors;
    private final Bounds bounds;
    private final BST<Point, Integer> distances;
    private final Point farthestPoint;

    Map(CharDigest re) {
        this.doors = new TreeSet<>();
        Point start = new Point(0, 0);
        findDoors(re, start);

        Bounds b = new Bounds(start, start);
        for (Point p : doors) {
            b = b.plus(p);
        }
        this.bounds = b;

        this.distances = distancesFrom(start);
        int maxD = 0;
        Point maxP = null;
        for (Point p : distances.keys()) {
            int d = distances.get(p);
            if (d > maxD) {
                maxD = d;
                maxP = p;
            }
        }
        farthestPoint = maxP;
    }

    public Point origin() {
        return bounds.min();
    }

    public int width() {
        return (int) bounds.width();
    }

    public int height() {
        return (int) bounds.height();
    }

    private static class Paint {
        final Point p;
        final int d;

        public Paint(Point p, int d) {
            this.p = p;
            this.d = d;
        }
    }

    private BST<Point, Integer> distancesFrom(Point start) {
        BST<Point, Integer> distances = new BST<>();
        Queue<Paint> q = new Queue<>();
        q.enqueue(new Paint(start, 0));
        while (! q.isEmpty()) {
            Paint paint = q.dequeue();
            Point point = paint.p;
            if (distances.contains(point)) continue;
            int distance = paint.d;
            distances.put(point, distance);
            for (Dir d : Dir.values()) {
                if (isDoor(point.go(d))) {
                    q.enqueue(new Paint(point.go(d, 2), distance + 1));
                }
            }
        }
        return distances;
    }

    Point farthestPoint() {
        return farthestPoint;
    }

    int distanceTo(Point p) {
        return distances.get(p);
    }

    int roomCount(int minDistance) {
        int count = 0;
        for (Point p : distances.keys()) {
            if (distances.get(p) >= minDistance) {
                count += 1;
            }
        }
        return count;
    }

    private boolean isDoor(Point p) {
        return doors.contains(p);
    }

    private void findDoors(CharDigest re, Point start) {
        Point p = start;
        while (re.hasNext()) {
            char c = re.next();
            if (c == '(') {
                findDoors(re, p);
            } else if (c == ')') {
                return; // done with this subexpression
            } else if (c == '|') {
                p = start;
            } else {
                Dir d = dir(c);
                doors.add(p.go(d));
                p = p.go(d, 2);
            }
        }
    }

    Iterable<Point> doors() {
        return doors;
    }

    Iterable<Point> rooms() {
        return distances.keys();
    }

    private Dir dir(char c) {
        switch (c) {
            case 'N': return UP;
            case 'S': return DOWN;
            case 'E': return RIGHT;
            case 'W': return LEFT;
        }
        throw new IllegalArgumentException("Unknown direction '" + c + "'");
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean withSpaces) {
        int minX = bounds.min().x - 1;
        int minY = bounds.min().y - 1;
        int maxX = bounds.max().x + 1;
        int maxY = bounds.max().y + 1;
        StringBuilder sb = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            if (y > minY) sb.append('\n');
            for (int x = minX; x <= maxX; x++) {
                if (x == 0 && y == 0) {
                    sb.append('X');
                } else if (doors.contains(new Point(x, y))) {
                    sb.append(withSpaces ? ' ' : x % 2 == 0 ? '-' : '|');
                } else if (x % 2 == 0 && y % 2 == 0) {
                    sb.append(withSpaces ? ' ' : '.');
                } else {
                    sb.append('#');
                }
            }
        }
        return sb.toString();
    }
}

package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.*;

import static com.barneyb.aoc2018.util.Dir.*;

public class Day20 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length()
//                , input.trim().length()
        );
    }

    static int partOne(String regex) {
        System.out.println(plotDoors(findDoors(regex)));
        return -1;
    }

    static TreeSet<Point> findDoors(String regex) {
        regex = regex.trim();
        CharDigest re = new CharDigest(regex.substring(1, regex.length() - 1).toCharArray());
        TreeSet<Point> doors = new TreeSet<>();
        findDoors(re, doors, new Point(0, 0));
        return doors;
    }

    static void findDoors(CharDigest re, TreeSet<Point> doors, Point start) {
        Point p = start;
        while (re.hasNext()) {
            char c = re.next();
            if (c == '(') {
                findDoors(re, doors, p);
            } else if (c == ')') {
                return; // done with this subexpression
            } else if (c == '|') {
                p = start;
            } else if (c == 'N') {
                doors.add(p.go(UP));
                p = p.go(UP, 2);
            } else if (c == 'S') {
                doors.add(p.go(DOWN));
                p = p.go(DOWN, 2);
            } else if (c == 'E') {
                doors.add(p.go(RIGHT));
                p = p.go(RIGHT, 2);
            } else if (c == 'W') {
                doors.add(p.go(LEFT));
                p = p.go(LEFT, 2);
            } else {
                throw new IllegalArgumentException("what? a '" + c + "'?");
            }
        }
    }

    static String plotDoors(TreeSet<Point> doors) {
        Bounds b = new Bounds(new Point(0, 0), new Point(0, 0));
        for (Point p : doors) {
            b = b.plus(p);
        }
        int minX = b.min().x - 1;
        int minY = b.min().y - 1;
        int maxX = b.max().x + 1;
        int maxY = b.max().y + 1;
        StringBuilder sb = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            if (y > minY) sb.append('\n');
            for (int x = minX; x <= maxX; x++) {
                if (x == 0 && y == 0) {
                    sb.append('X');
                } else if (doors.contains(new Point(x, y))) {
                    sb.append(x % 2 == 0 ? '-' : '|');
                } else if (x % 2 == 0 && y % 2 == 0) {
                    sb.append('.');
                } else {
                    sb.append('#');
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args)  {
        Day20 d = new Day20();
        String input = FileUtils.readFile("day20/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %d ms%n", a, e);
    }

}

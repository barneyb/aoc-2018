package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.Stack;
import com.barneyb.aoc2018.util.TreeSet;

public class MinDistancePlot {

    public static final int UNKNOWN = -2;
    public static final int CONFLICT = -1;

    private final int width, height;
    private final TreeSet<Point> seeds;
    private final int[] plot;

    public MinDistancePlot(Point[] points) {
        Point max = Day06.max(points);
        width = max.x + 1;
        height = max.y + 1;
        seeds = new TreeSet<>();
        plot = new int[width * height];
        for (int i = 0; i < plot.length; i++) {
            plot[i] = UNKNOWN;
        }
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            seeds.add(p);
            plot[index(p)] = i;
        }
        Point p;
        boolean conflict;
        int md, minMd;
        Stack<Integer> coords;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                p = new Point(x, y);
                minMd = Integer.MAX_VALUE;
                coords = new Stack<>();
                for (int i = 0; i < points.length; i++) {
                    md = points[i].md(p);
                    if (md < minMd) {
                        minMd = md;
                        coords.clear();
                        coords.push(i);
                    } else if (md == minMd) {
                        coords.push(i);
                    }
                }
                plot[index(p)] = coords.size() == 1 ? coords.pop() : CONFLICT;
            }
        }
    }

    private int index(Point p) {
        return p.y * width + p.x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Point p;
        int coord;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                p = new Point(x, y);
                coord = plot[index(p)];
                sb.append(coord == UNKNOWN ? '?' : coord == CONFLICT ? '.' : (char) (coord + (seeds.contains(p) ? 65 : 97)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Histogram;
import com.barneyb.aoc2018.util.Stack;
import com.barneyb.aoc2018.util.TreeSet;

public class ClosestPlot {

    public static final int UNKNOWN = -2;
    public static final int CONFLICT = -1;

    private final int width, height;
    private final TreeSet<Point> seeds;
    private final int[] plot;

    public ClosestPlot(Point[] points) {
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
        return index(p.x, p.y);
    }

    private int index(int x, int y) {
        return y * width + x;
    }

    public TreeSet<Integer> getBoundaryAreas() {
        TreeSet<Integer> areas = new TreeSet<>();
        int n;
        for (int x = 0; x < width; x++) {
            n = plot[index(x, 0)];
            areas.add(n);
            n = plot[index(x, height - 1)];
            areas.add(n);
        }
        for (int y = 0; y < height; y++) {
            n = plot[index(0, y)];
            areas.add(n);
            n = plot[index(width - 1, y)];
            areas.add(n);
        }
        areas.delete(CONFLICT);
        return areas;
    }

    public BST<Integer, Integer> getSizes() {
        Histogram<Integer> sizes = new Histogram<>();
        for (int i : plot) {
            sizes.count(i);
        }
        sizes.delete(CONFLICT);
        return sizes;
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

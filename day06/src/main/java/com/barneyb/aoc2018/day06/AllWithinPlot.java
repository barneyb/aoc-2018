package com.barneyb.aoc2018.day06;

import com.barneyb.aoc2018.util.Point;

class AllWithinPlot {

    private int size;

    public AllWithinPlot(Plotter plotter, int distanceCap) {
        Point max = plotter.max();
        int width = max.x + 1;
        int height = max.y + 1;
        Point p;
        int d;
        for (int x = 0; x < width; x++) {
            pointLoop:
            for (int y = 0; y < height; y++) {
                p = new Point(x, y);
                d = 0;
                for (Point c : plotter.points()) {
                    d += p.md(c);
                    if (d >= distanceCap) {
                        continue pointLoop;
                    }
                }
                // we're under!
                size += 1;
            }
        }
    }

    public int getSize() {
        return size;
    }

}

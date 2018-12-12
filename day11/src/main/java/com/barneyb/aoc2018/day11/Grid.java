package com.barneyb.aoc2018.day11;

import com.barneyb.aoc2018.util.Point;

public class Grid {

    private static final int LEG = 300;

    private int[][] grid = new int[LEG + 1][];

    public Grid(int serial) {
        for (int y = 1; y <= LEG; y++) {
            grid[y] = new int[LEG + 1];
            for (int x = 1; x <= LEG; x++) {
                grid[y][x] = level(x, y, serial);
            }
        }
    }

    static int level(int x, int y, int serial) {
        int rackId = x + 10;
        int level = rackId * y;
        level += serial;
        level *= rackId;
        level /= 100;
        level %= 10;
        return level - 5;
    }

    public Square mostPowerfulPoint() {
        return mostPowerfulPoint(3, 3);
    }

    public Square mostPowerfulPoint(int minLeg, int maxLeg) {
        int max = Integer.MIN_VALUE;
        Square best = null;
        for (int leg = minLeg; leg <= maxLeg; leg++) {
            for (int y = 1; y < LEG - leg; y++) {
                for (int x = 1; x < LEG - leg; x++) {
                    int sum = 0;
                    for (int dy = 0; dy < leg; dy++) {
                        for (int dx = 0; dx < leg; dx++) {
                            sum += grid[y + dy][x + dx];
                        }
                    }
                    if (sum > max) {
                        max = sum;
                        best = new Square(new Point(x, y), leg);
                    }
                }
            }
        }
        return best;
    }
}

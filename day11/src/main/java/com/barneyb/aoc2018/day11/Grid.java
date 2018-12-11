package com.barneyb.aoc2018.day11;

import com.barneyb.aoc2018.util.Point;

public class Grid {

    private static final int LEG = 300;

    private int[][] grid = new int[LEG + 1][];

    public Grid(int serial) {
        for (int y = 1; y <= LEG; y++) {
            grid[y] = new int[LEG + 1];
            for (int x = 1; x <= LEG; x++) {
                grid[y][x] = new Cell(x, y).level(serial);
            }
        }
    }

    public Point mostPowerfulPoint() {
        int max = Integer.MIN_VALUE;
        Point best = null;
        for (int y = 1; y <= LEG - 2; y++) {
            for (int x = 1; x <= LEG - 2; x++) {
                int sum = grid[y    ][x    ]
                        + grid[y    ][x + 1]
                        + grid[y    ][x + 2]
                        + grid[y + 1][x    ]
                        + grid[y + 1][x + 1]
                        + grid[y + 1][x + 2]
                        + grid[y + 2][x    ]
                        + grid[y + 2][x + 1]
                        + grid[y + 2][x + 2];
                if (sum > max) {
                    max = sum;
                    best = new Point(x, y);
                }
            }
        }
        return best;
    }
}

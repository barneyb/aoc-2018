package com.barneyb.aoc2018.day23;

class ScaledPlot {

    static final int MAX_SIZE = 200;

    final Axis xAxis, yAxis;
    final Range scaledX, scaledY;
    final int[][] grid;
    Range vRange = Range.inclusive(0, 0); // everything starts at zero

    ScaledPlot(Axis xAxis, Axis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        scaledX = scale(xAxis.range);
        scaledY = scale(yAxis.range);
        grid = new int[scaledY.size()][];
        for (int y = 0; y < grid.length; y++) {
            grid[y] = new int[scaledX.size()];
        }
    }

    private Range scale(Range r) {
        return r.size() < MAX_SIZE ? r.toZero()
                : Range.halfOpen(0, MAX_SIZE);
    }

    public void inc(int x, int y) {
        int sx = scaledX.unscale(xAxis.range.scale(x));
        int sy = scaledY.unscale(yAxis.range.scale(y));
        vRange = vRange.plus(grid[sy][sx] += 1);
    }

    public int scaledGet(int x, int y) {
        return grid[y][x];
    }

    public int unscaleX(int x) {
        return xAxis.range.unscale(scaledX.scale(x));
    }

    public int unscaleY(int y) {
        return yAxis.range.unscale(scaledY.scale(y));
    }

    public double xFactor() {
        return 1.0 * scaledX.size() / xAxis.range.size();
    }

    public double yFactor() {
        return 1.0 * scaledY.size() / yAxis.range.size();
    }


//    private static final char[] GAMUT = ".:-=+*#%@".toCharArray();
    private static final char[] GAMUT = ".'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$".toCharArray();
    private static final Range GAMUT_RANGE = Range.halfOpen(0, GAMUT.length);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int xl = Math.max(
                String.format("%,d", xAxis.range.start()).length(),
                String.format("%,d", xAxis.range.end()).length()
        );
        int yl = Math.max(
                String.format("%,d", yAxis.range.start()).length(),
                String.format("%,d", yAxis.range.end()).length()
        );
        sb.append(String.format("%s-%s plane", xAxis.label, yAxis.label)).append('\n');
        sb.append(String.format("%s factor: %10.9f (%d per plot point)%n", xAxis.label, xFactor(), (int) (1 / xFactor())));
        sb.append(String.format("%s factor: %10.9f (%d per plot point)%n", yAxis.label, yFactor(), (int) (1 / yFactor())));
        sb.append(String.format("%" + yl + "s  ", ""));
        for (int x : scaledX.by(xl + 4)) {
            sb.append(String.format("| %-," + (xl + 2) + "d", unscaleX(x)));
        }
        sb.append("\n");
        for (int y : scaledY) {
            sb.append(String.format("%," + yl + "d |", unscaleY(y)));
            for (int cell : grid[y]) {
                sb.append(cell == 0 ? ' ' : GAMUT[GAMUT_RANGE.unscale(vRange.scale(cell))]);
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

}

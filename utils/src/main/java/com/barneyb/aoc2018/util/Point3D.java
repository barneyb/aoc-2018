package com.barneyb.aoc2018.util;

public class Point3D {

    public static Point3D parse(String input) {
        return scan(new Scanner(input));
    }

    public static Point3D scan(Scanner s) {
        boolean hasParen = s.probe('(');
        if (hasParen) s.skip('(');
        Point3D p = new Point3D(
                s.skipWS().readInt(),
                s.skip(',').skipWS().readInt(),
                s.skip(',').skipWS().readInt()
        );
        if (hasParen) s.skip(')');
        return p;
    }

    public final int x, y, z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int md(Point3D p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y) + Math.abs(z - p.z);
    }

}

package com.barneyb.aoc2018.util;

public class Point3D extends Vector {

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

    public Point3D(int x, int y, int z) {
        super(x, y, z);
    }

    public int x() {
        return dim(0);
    }

    public int y() {
        return dim(1);
    }

    public int z() {
        return dim(2);
    }

}

package com.barneyb.aoc2018.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorTest {

    @Test
    public void md2() {
        Point p = new Point(0, 0);
        Point q = new Point(3, 4);
        Vector v = new Vector(0, 0);
        Vector r = new Vector(3, 4);
        assertEquals(p.md(q), v.md(r));
        assertEquals(p.md(q), r.md(v));
    }

    @Test
    public void md3() {
        Point3D p = new Point3D(0, 0, 0);
        Point3D q = new Point3D(3, 4, 5);
        Vector v = new Vector(0, 0, 0);
        Vector r = new Vector(3, 4, 5);
        assertEquals(p.md(q), v.md(r));
        assertEquals(p.md(q), r.md(v));
    }

}
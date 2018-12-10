package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Scanner;

public class Particle {

    public static Particle parse(String str) {
        return scan(new Scanner(str));
    }

    public static Particle scan(Scanner s) {
        //position=<-6, 10> velocity=< 2, -2>
        return new Particle(
                new Point(
                        s.skip("position=<").skipWS().readInt(),
                        s.skip(",").skipWS().readInt()
                ),
                new Point(
                        s.skip("> velocity=<").skipWS().readInt(),
                        s.skip(",").skipWS().readInt()
                )
        );
    }

    Point pos;
    Point vel;

    public Particle(Point pos, Point vel) {
        this.pos = pos;
        this.vel = vel;
    }

    public void tick() {
        pos = pos.plus(vel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Particle)) return false;

        Particle particle = (Particle) o;

        if (!pos.equals(particle.pos)) return false;
        return vel.equals(particle.vel);
    }

    @Override
    public int hashCode() {
        int result = pos.hashCode();
        result = 31 * result + vel.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("position=<%d, %d> velocity=<%d, %d>", pos.x, pos.y, vel.x, vel.y);
    }
}

package com.barneyb.aoc2018.day10;

import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Scanner;

public class Light {

    public static Light parse(String str) {
        return scan(new Scanner(str));
    }

    public static Light scan(Scanner s) {
        //position=<-6, 10> velocity=< 2, -2>
        return new Light(
                Point.scan(s.skip("position=<")),
                Point.scan(s.skip("> velocity=<"))
        );
    }

    Point pos;
    Point vel;

    public Light(Point pos, Point vel) {
        this.pos = pos;
        this.vel = vel;
    }

    public void tick(int steps) {
        pos = pos.plus(vel.times(steps));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Light)) return false;

        Light light = (Light) o;

        if (!pos.equals(light.pos)) return false;
        return vel.equals(light.vel);
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

package com.barneyb.aoc2018.day03;

import com.barneyb.aoc2018.util.Scanner;

class Claim {

    public static Claim parse(String str) {
        return scan(new Scanner(str));
    }

    public static Claim scan(Scanner s) {
        return new Claim(
                s.skip("#").readInt(),
                s.skip(" @ ").readInt(),
                s.skip(",").readInt(),
                s.skip(": ").readInt(),
                s.skip("x").readInt()
        );
    }

    final int id, left, top, width, height;

    public Claim(int id, int left, int top, int width, int height) {
        this.id = id;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;
        Claim claim = (Claim) o;
        if (id != claim.id) return false;
        if (left != claim.left) return false;
        if (top != claim.top) return false;
        if (width != claim.width) return false;
        return height == claim.height;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + left;
        result = 31 * result + top;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return String.format("#%d @ %d,%d: %dx%d", id, left, top, width, height);
    }
}

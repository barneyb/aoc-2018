package com.barneyb.aoc2018.day03;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Claim {

    private static final Pattern PATTERN_STRING = Pattern.compile("#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)");
    public static Claim fromString(String str) {
        //#123 @ 3,2: 5x4
        Matcher m = PATTERN_STRING.matcher(str);
        if (! m.matches()) {
            throw new RuntimeException("Malformed Input: '" + str + "'");
        }
        return new Claim(
                Integer.parseInt(m.group(1)),
                Integer.parseInt(m.group(2)),
                Integer.parseInt(m.group(3)),
                Integer.parseInt(m.group(4)),
                Integer.parseInt(m.group(5))
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
        return id == claim.id &&
                left == claim.left &&
                top == claim.top &&
                width == claim.width &&
                height == claim.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, left, top, width, height);
    }

    @Override
    public String toString() {
        return String.format("#%d @ %d,%d: %dx%d", id, left, top, width, height);
    }
}

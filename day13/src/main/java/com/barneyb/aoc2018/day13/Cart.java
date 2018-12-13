package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;

public class Cart implements Comparable<Cart> {

    private final char label;
    private final char on;
    private Point pos;
    private Dir dir;
    private Turn turn = Turn.LEFT;

    public Cart(char label, char on, Point pos, Dir dir) {
        this.label = label;
        this.on = on;
        this.pos = pos;
        this.dir = dir;
    }

    public char label() {
        return label;
    }

    public char on() {
        return on;
    }

    public Dir dir() {
        return dir;
    }

    public Point pos() {
        return pos;
    }

    public int x() {
        return pos.x;
    }

    public int y() {
        return pos.y;
    }

    @Override
    public int compareTo(Cart o) {
        // points naturally have "english reading" order, so just delegate
        return pos.compareTo(o.pos);
    }

}

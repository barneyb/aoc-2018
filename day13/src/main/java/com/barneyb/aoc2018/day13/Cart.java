package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;

class Cart implements Comparable<Cart> {

    private final char label;
    private Point pos;
    private char on;
    private Dir dir;
    private Turn turn = Turn.LEFT;

    Cart(char label, Point pos, char on, Dir dir) {
        this.label = label;
        update(pos, on, dir);
    }

    void update(Point pos, char on, Dir dir, Turn turn) {
        update(pos, on, dir);
        this.turn = turn;
    }

    void update(Point pos, char on, Dir dir) {
        update(pos, on);
        this.dir = dir;
    }

    void update(Point pos, char on) {
        this.pos = pos;
        this.on = on;
    }

    Point next() {
        return pos.plus(dir.step);
    }

    char label() {
        return label;
    }

    char on() {
        return on;
    }

    Dir dir() {
        return dir;
    }

    Point pos() {
        return pos;
    }

    int x() {
        return pos.x;
    }

    int y() {
        return pos.y;
    }

    Turn turn() {
        return turn;
    }

    @Override
    public int compareTo(Cart o) {
        // points naturally have "english reading" order, so just delegate
        return pos.compareTo(o.pos);
    }

}

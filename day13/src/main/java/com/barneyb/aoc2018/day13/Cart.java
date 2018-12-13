package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;

public class Cart implements Comparable<Cart> {

    private final char label;
    private Point loc;
    private Dir dir;
    private Turn turn = Turn.LEFT;

    public Cart(char label, Point loc, Dir dir) {
        this.label = label;
        this.loc = loc;
        this.dir = dir;
    }

    @Override
    public int compareTo(Cart o) {
        // points naturally have "english reading" order, so just delegate
        return loc.compareTo(o.loc);
    }

}

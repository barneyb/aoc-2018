package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Queue;

public class Map {

    public static Map parse(String input) {
        Queue<String> raw = new Queue<>(input.split("\n"));
        Queue<String> keepers = new Queue<>();
        for (String l : raw) {
            if (l.trim().length() > 0) {
                keepers.enqueue(l);
            }
        }
        String[] grid = new String[keepers.size()];
        for (int i = 0, l = grid.length; i < l; i++) {
            grid[i] = keepers.dequeue();
        }
        return new Map(grid);
    }

    private final StringBuilder[] grid;
    private final Cart[] carts;

    public Map(String[] grid) {
        this.grid = new StringBuilder[grid.length];
        Queue<Cart> carts = new Queue<>();
        char cartIndex = 'A';
        for (int y = 0, l = grid.length; y < l; y++) {
            StringBuilder sb = new StringBuilder(grid[y]);
            this.grid[y] = sb;
            for (int x = sb.length() - 1; x >= 0; x--) {
                char c = sb.charAt(x);
                for (Dir d : Dir.values()) {
                    if (c == d.indicator) {
                        sb.setCharAt(x, cartIndex);
                        carts.enqueue(new Cart(cartIndex++, new Point(x, y), d));
                        break;
                    }
                }
            }
        }
        this.carts = new Cart[carts.size()];
        for (int i = 0, l = carts.size(); i < l; i++) {
            this.carts[i] = carts.dequeue();
        }
    }

    public Point locationOfFirstCrash() {
        // todo: implement, yo!
        return new Point(0, 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (StringBuilder it : grid) {
            sb.append(it).append("\n");
        }
        return sb.toString();
    }
}

package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Queue;
import com.barneyb.aoc2018.util.Sort;

import static com.barneyb.aoc2018.day13.Dir.NORTH;
import static com.barneyb.aoc2018.day13.Dir.SOUTH;

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
    private final Queue<Crash> crashes = new Queue<>();

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
                        char on;
                        if (d == NORTH || d == SOUTH) {
                            if (x == 0) {
                                on = '|';
                            } else {
                                char n = sb.charAt(x - 1);
                                on = n == '-' || n == '+' ? '+' : '|';
                            }
                        }
                        else {
                            if (y == 0) {
                                on = '-';
                            } else {
                                char n = this.grid[y - 1].charAt(x);
                                on = n == '|' || n == '+' ? '+' : '-';
                            }
                        }
                        sb.setCharAt(x, cartIndex);
                        carts.enqueue(new Cart(cartIndex, on, new Point(x, y), d));
                        cartIndex += 1;
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

    void tick() {
        Sort.sort(carts);
        for (Cart c : carts) {

        }
    }

    public int crashCount() {
        return crashes.size();
    }

    public Point locationOfFirstCrash() {
        return crashes.iterator().next().loc;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean includeCarts) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            StringBuilder it = grid[y];
            if (! includeCarts) {
                it = new StringBuilder(it);
                for (Cart c : carts) {
                    if (c.y() != y) continue;
                    it.setCharAt(c.x(), c.on());
                }
            }
            sb.append(it).append("\n");
        }
        return sb.toString();
    }
}

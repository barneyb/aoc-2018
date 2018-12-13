package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Queue;
import com.barneyb.aoc2018.util.TreeSet;

import static com.barneyb.aoc2018.day13.Dir.NORTH;
import static com.barneyb.aoc2018.day13.Dir.SOUTH;

class Map {

    static Map parse(String input) {
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
    private TreeSet<Cart> carts;
    private final Queue<Crash> crashes = new Queue<>();

    Map(String[] grid) {
        this.grid = new StringBuilder[grid.length];
        this.carts = new TreeSet<>();
        char cartIndex = 'A';
        for (int y = 0; y < grid.length; y++) {
            StringBuilder sb = new StringBuilder(grid[y]);
            this.grid[y] = sb;
            for (int x = 0, l = sb.length(); x < l; x++) {
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
                        carts.add(new Cart(cartIndex, new Point(x, y), on, d));
                        cartIndex += 1;
                        break;
                    }
                }
            }
        }
    }

    void tick() {
        TreeSet<Cart> nextCarts = new TreeSet<>();
        TreeSet<Character> boom = new TreeSet<>();
        for (Cart c : carts) {
            if (boom.contains(c.label())) continue;
            grid[c.y()].setCharAt(c.x(), c.on());
            Point p = c.next();
            char at = charAt(p);
            switch (at) {
                case '/':
                case '\\':
                    c.update(p, at, c.dir().curve(at));
                    nextCarts.add(c);
                    break;
                case '-':
                case '|':
                    c.update(p, at);
                    nextCarts.add(c);
                    break;
                case '+':
                    c.update(p, at, c.dir().turn(c.turn()), c.turn().next());
                    nextCarts.add(c);
                    break;
                case ' ':
                    throw new RuntimeException("Um, carts can't go on spaces");
                default:
                    crashes.enqueue(new Crash(p, c.label(), at));
                    boom.add(at);
                    break;
            }
            grid[p.y].setCharAt(p.x, c.label());
        }
        carts = nextCarts;
    }

    private char charAt(Point p) {
        return grid[p.y].charAt(p.x);
    }

    int crashCount() {
        return crashes.size();
    }

    Point locationOfFirstCrash() {
        return crashes.iterator().next().loc;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    String toString(boolean includeCarts) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            if (y > 0) sb.append("\n");
            StringBuilder it = grid[y];
            if (! includeCarts) {
                it = new StringBuilder(it);
                for (Cart c : carts) {
                    if (c.y() != y) continue;
                    it.setCharAt(c.x(), c.on());
                }
            }
            sb.append(it);
        }
        return sb.toString();
    }
}

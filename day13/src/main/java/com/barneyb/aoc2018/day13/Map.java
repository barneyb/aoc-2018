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

    private final char[][] grid;
    private TreeSet<Cart> carts;
    private final Queue<Crash> crashes = new Queue<>();

    Map(String[] grid) {
        this.grid = new char[grid.length][];
        this.carts = new TreeSet<>();
        char cartIndex = 'A';
        for (int y = 0; y < grid.length; y++) {
            char[] row = this.grid[y] = grid[y].toCharArray();
            for (int x = 0, l = row.length; x < l; x++) {
                char c = row[x];
                for (Dir d : Dir.values()) {
                    if (c == d.indicator) {
                        char on;
                        if (d == NORTH || d == SOUTH) {
                            if (x == 0) {
                                on = '|';
                            } else {
                                char n = row[x - 1];
                                on = n == '-' || n == '+' ? '+' : '|';
                            }
                        }
                        else {
                            if (y == 0) {
                                on = '-';
                            } else {
                                char n = this.grid[y - 1][x];
                                on = n == '|' || n == '+' ? '+' : '-';
                            }
                        }
                        row[x] = cartIndex;
                        carts.add(new Cart(cartIndex, new Point(x, y), on, d));
                        cartIndex += 1;
                        break;
                    }
                }
            }
        }
    }

    void tick() {
        TreeSet<Character> boom = new TreeSet<>();
        for (Cart c : carts) {
            erase(c);
            Point p = c.next();
            char at = charAt(p);
            switch (at) {
                case '/':
                case '\\':
                    c.update(p, at, c.dir().curve(at));
                    break;
                case '-':
                case '|':
                    c.update(p, at);
                    break;
                case '+':
                    c.update(p, at, c.dir().turn(c.turn()), c.turn().next());
                    break;
                case ' ':
                    throw new RuntimeException("Um, carts can't go on spaces");
                default:
                    Crash cr = new Crash(p, c.label(), at);
                    crashes.enqueue(cr);
                    boom.add(cr.a);
                    boom.add(cr.b);
                    break;
            }
            draw(p, c.label());
        }
        TreeSet<Cart> nextCarts = new TreeSet<>();
        for (Cart c : carts) {
            if (boom.contains(c.label())) {
                erase(c);
            } else {
                nextCarts.add(c);
            }
        }
        carts = nextCarts;
    }

    private void erase(Cart c) {
        draw(c.pos(), c.on());
    }

    private void draw(Point p, char on) {
        draw(p.x, p.y, on);
    }

    private void draw(int x, int y, char on) {
        grid[y][x] = on;
    }

    private char charAt(Point p) {
        return grid[p.y][p.x];
    }

    int crashCount() {
        return crashes.size();
    }

    Point locationOfFirstCrash() {
        return crashes.iterator().next().loc;
    }

    public int cartCount() {
        return carts.size();
    }

    public Point locationOfFinalCart() {
        return carts.min().pos();
    }

    @Override
    public String toString() {
        return toString(true);
    }

    String toString(boolean includeCarts) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            if (y > 0) sb.append("\n");
            char[] it = grid[y];
            if (includeCarts) {
                sb.append(it);
            } else {
                char[] clean = new char[it.length];
                System.arraycopy(it, 0, clean, 0, it.length);
                for (Cart c : carts) {
                    if (c.y() != y) continue;
                    clean[c.x()] = c.on();
                }
                sb.append(clean);
            }
        }
        return sb.toString();
    }
}

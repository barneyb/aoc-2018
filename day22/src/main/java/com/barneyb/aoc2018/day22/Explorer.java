package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.Dir;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.TreeSet;

import static com.barneyb.aoc2018.day22.LoadOut.TORCH;
import static com.barneyb.aoc2018.util.Dir.DOWN;
import static com.barneyb.aoc2018.util.Dir.RIGHT;

class Explorer {

    static final int COST_MOVE = 1;
    static final int COST_EQUIP = 7;

    private static class Route {
        final Point at;
        final LoadOut loadOut;
        final int cost;

        private Route() {
            this(Map.ORIGIN, TORCH, 0);
        }

        private Route(Point at, LoadOut loadOut, int cost) {
            this.at = at;
            this.loadOut = loadOut;
            this.cost = cost;
        }

        Route equip(LoadOut loadOut) {
            if (this.loadOut == loadOut) return this;
//            System.out.printf("Equip %s, cost %d%n", loadOut, cost + COST_EQUIP);
            return new Route(at, loadOut, cost + COST_EQUIP);
        }

        Route move(Dir d) {
            return move(at.go(d));
        }

        Route move(Point p) {
            if (at.equals(p)) return this;
//            System.out.printf("Move to %s, cost %d%n", p, cost + COST_MOVE);
            return new Route(p, loadOut, cost + COST_MOVE);
        }

    }

    private final Map map;
    private TreeSet<Point> visited = new TreeSet<>();
    private int fastest = -1;

    Explorer(Map map) {
        this.map = map;
    }

    int getBound() {
        Route r = new Route();
        int goal = map.target().x;
        while (r.at.x < goal) {
            r = stepTo(r, RIGHT);
        }
        goal = map.target().y;
        while (r.at.y < goal) {
            r = stepTo(r, DOWN);
        }
        return r.cost;
    }

    private Route stepTo(Route r, Dir d) {
        Point q = r.at.go(d);
        Region reg = map.region(q);
        if (! reg.allowed(r.loadOut)) {
            r = r.equip(reg.otherLoadout(r.loadOut));
        }
        r = r.move(q);
        return r;
    }

    public int fastest() {
        if (fastest >= 0) return fastest;
        // todo: calculate
        return fastest;
    }

}

package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Dir;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Queue;

import static com.barneyb.aoc2018.day22.Tool.TORCH;
import static com.barneyb.aoc2018.util.Dir.*;

class Explorer {

    static final int COST_MOVE = 1;
    static final int COST_EQUIP = 7;

    private static class Route implements Comparable<Route> {
        final Point at;
        final Tool tool;
        final int cost;

        private Route() {
            this(Map.ORIGIN, TORCH, 0);
        }

        private Route(Point at, Tool tool, int cost) {
            this.at = at;
            this.tool = tool;
            this.cost = cost;
        }

        Route equip(Tool tool) {
            if (this.tool == tool) return this;
//            System.out.printf("Equip %s, cost %d%n", tool, cost + COST_EQUIP);
            return new Route(at, tool, cost + COST_EQUIP);
        }

        Route move(Dir d) {
            return move(at.go(d));
        }

        Route move(Point p) {
            if (at.equals(p)) return this;
//            System.out.printf("Move to %s, cost %d%n", p, cost + COST_MOVE);
            return new Route(p, tool, cost + COST_MOVE);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Route)) return false;
            Route route = (Route) o;
            if (!at.equals(route.at)) return false;
            return tool == route.tool;
        }

        @Override
        public int hashCode() {
            int result = at.hashCode();
            result = 31 * result + tool.hashCode();
            return result;
        }

        @Override
        public int compareTo(Route o) {
            int c = at.compareTo(o.at);
            if (c != 0) return c;
            return tool.compareTo(o.tool);
        }
    }

    private final Map map;
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
        if (! reg.allowed(r.tool)) {
            r = r.equip(reg.otherTool(r.tool));
        }
        r = r.move(q);
        return r;
    }

    public int fastest() {
        if (fastest >= 0) return fastest;
        fastest = getBound(); // a place to start....
        final Point target = map.target();
        BST<Route, Integer> costs = new BST<>();
        Queue<Route> queue = new Queue<>();
        queue.enqueue(new Route());
        while (! queue.isEmpty()) {
            Route r = queue.dequeue();
            if (r.cost >= fastest) continue;
            Point p = r.at;
            if (p.equals(target)) {
                if (r.tool != TORCH) r = r.equip(TORCH);
                System.out.printf("%s, %s, %d (%d to go)%n", p, r.tool, r.cost, queue.size());
                if (r.cost < fastest) {
                    fastest = r.cost;
                    System.out.println("NEW BEST ^");
                }
                continue;
            }
            Integer bc = costs.get(r);
            if (bc != null && bc <= r.cost) {
                continue;
            }
            costs.put(r, r.cost);
            if (p.x > 0) queue.enqueue(stepTo(r, LEFT));
            if (p.y > 0) queue.enqueue(stepTo(r, UP));
            queue.enqueue(stepTo(r, RIGHT));
            queue.enqueue(stepTo(r, DOWN));
        }
        return fastest;
    }

}

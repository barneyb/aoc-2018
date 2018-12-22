package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.Dir;
import com.barneyb.aoc2018.util.Point;

import static com.barneyb.aoc2018.day22.Tool.TORCH;
import static com.barneyb.aoc2018.util.Dir.DOWN;
import static com.barneyb.aoc2018.util.Dir.RIGHT;

class Explorer {

    static final int COST_MOVE = 1;
    static final int COST_EQUIP = 7;

    private static class State implements Comparable<State> {
        final Point at;
        final Tool tool;

        private State() {
            this(Map.ORIGIN, TORCH);
        }

        private State(Point at, Tool tool) {
            this.at = at;
            this.tool = tool;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State state = (State) o;
            if (!at.equals(state.at)) return false;
            return tool == state.tool;
        }

        @Override
        public int hashCode() {
            int result = at.hashCode();
            result = 31 * result + tool.hashCode();
            return result;
        }

        @Override
        public int compareTo(State o) {
            int c = at.compareTo(o.at);
            if (c != 0) return c;
            return tool.compareTo(o.tool);
        }
    }

    private class Route extends State {
        final int cost;

        Route() {
            super();
            cost = 0;
        }

        public Route(Point at, Tool tool, int cost) {
            super(at, tool);
            this.cost = cost;
        }

        Route equip(Tool tool) {
            if (this.tool == tool) return this;
            return new Route(at, tool, cost + COST_EQUIP);
        }

        Route moveTo(Point to) {
            if (at.equals(to)) return this;
            return new Route(to, tool, cost + COST_MOVE);
        }

        boolean canStep(Dir d) {
            return map.region(at.go(d)).allowed(tool);
        }

        Route step(Dir d) {
            Point p = at.go(d);
            Region reg = map.region(p);
            Route r = this;
            if (! reg.allowed(tool)) {
                r = r.equip(reg.otherTool(tool));
            }
            return r.moveTo(p);
        }
    }

    private final Map map;
    private int fastest = -1;

    Explorer(Map map) {
        this.map = map;
    }

    int getTrivialCost() {
        Route r = new Route();
        int goal = map.target().x;
        while (r.at.x < goal) {
            r = r.step(RIGHT);
        }
        goal = map.target().y;
        while (r.at.y < goal) {
            r = r.step(DOWN);
        }
        return r.cost;
    }

    public int fastest() {
        if (fastest >= 0) return fastest;
        fastest = getTrivialCost(); // a place to start....
        return fastest;
    }

}

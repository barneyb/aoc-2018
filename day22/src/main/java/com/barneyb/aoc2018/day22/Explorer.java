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

    private static class State implements Comparable<State> {
        final Point at;
        final Tool tool;
        final int cost;

        private State() {
            this(Map.ORIGIN, TORCH, 0);
        }

        private State(Point at, Tool tool, int cost) {
            this.at = at;
            this.tool = tool;
            this.cost = cost;
        }

        State equip(Tool tool) {
            if (this.tool == tool) return this;
            return new State(at, tool, cost + COST_EQUIP);
        }

        State move(Point p) {
            if (at.equals(p)) return this;
            return new State(p, tool, cost + COST_MOVE);
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

    private final Map map;
    private int fastest = -1;

    Explorer(Map map) {
        this.map = map;
    }

    int getBound() {
        State s = new State();
        int goal = map.target().x;
        while (s.at.x < goal) {
            s = stepTo(s, RIGHT);
        }
        goal = map.target().y;
        while (s.at.y < goal) {
            s = stepTo(s, DOWN);
        }
        return s.cost;
    }

    private State stepTo(State s, Dir d) {
        Point p = s.at.go(d);
        Region r = map.region(p);
        if (! r.allowed(s.tool)) {
            s = s.equip(r.otherTool(s.tool));
        }
        s = s.move(p);
        return s;
    }

    public int fastest() {
        if (fastest >= 0) return fastest;
        fastest = getBound(); // a place to start....
        final Point target = map.target();
        BST<State, Integer> costs = new BST<>();
        Queue<State> queue = new Queue<>();
        queue.enqueue(new State());
        while (! queue.isEmpty()) {
            State s = queue.dequeue();
            if (s.cost >= fastest) continue;
            Point p = s.at;
            if (p.equals(target)) {
                if (s.tool != TORCH) s = s.equip(TORCH);
                System.out.printf("%s, %s, %d (%d to go)%n", p, s.tool, s.cost, queue.size());
                if (s.cost < fastest) {
                    fastest = s.cost;
                    System.out.println("NEW BEST ^");
                }
                continue;
            }
            Integer bc = costs.get(s);
            if (bc != null && bc <= s.cost) {
                continue;
            }
            costs.put(s, s.cost);
            if (p.x > 0) queue.enqueue(stepTo(s, LEFT));
            if (p.y > 0) queue.enqueue(stepTo(s, UP));
            queue.enqueue(stepTo(s, RIGHT));
            queue.enqueue(stepTo(s, DOWN));
            queue.enqueue(s.equip(map.region(s.at).otherTool(s.tool)));
        }
        return fastest;
    }

}

package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Dir;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Queue;

import static com.barneyb.aoc2018.day22.Tool.TORCH;
import static com.barneyb.aoc2018.util.Dir.*;

class Explorer {

    static final int TIME_MOVE = 1;
    static final int TIME_EQUIP = 7;

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
        final int time;

        Route() {
            super();
            time = 0;
        }

        public Route(Point at, Tool tool, int time) {
            super(at, tool);
            this.time = time;
        }

        Route equip(Tool tool) {
            if (this.tool == tool) return this;
            return new Route(at, tool, time + TIME_EQUIP);
        }

        Route move(Dir d) {
            return moveTo(at.go(d));
        }

        Route moveTo(Point to) {
            if (at.equals(to)) return this;
            return new Route(to, tool, time + TIME_MOVE);
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

    int getTrivialTime() {
        Route r = new Route();
        int goal = map.target().x;
        while (r.at.x < goal) {
            r = r.step(RIGHT);
        }
        goal = map.target().y;
        while (r.at.y < goal) {
            r = r.step(DOWN);
        }
        return r.time;
    }

    public int fastest() {
        if (fastest >= 0) return fastest;
        fastest = getTrivialTime(); // a place to start....
        BST<State, Integer> times = new BST<>();
        Queue<Route> toProcess = new Queue<>();
        toProcess.enqueue(new Route());
//        int stepCount = 0;
//        int routeCount = 0;
        while (! toProcess.isEmpty()) {
//            stepCount += 1;
            Route r = toProcess.dequeue();

            // see if we found him
            if (r.at.equals(map.target())) {
                // ensure the torch is equipped
                if (r.tool == TORCH) {
//                    routeCount += 1;
//                    System.out.printf("After %d steps: %d routes found (%d vs %d min)%n", stepCount, routeCount, r.time, fastest);
                    if (r.time < fastest) fastest = r.time;
                }
                continue;
            }

            // ensure we're not already too slow
            if (r.time >= fastest) continue;
            Region region = map.region(r.at);
            // ensure the tool is allowed
            if (! region.allowed(r.tool)) continue;
            // ensure we haven't been here, but faster
            Integer time = times.get(r);
            if (time != null && time <= r.time) continue;

            // ok, this one's sound
            // write it down
            times.put(r, r.time);

            // traverse out
            toProcess.enqueue(r.equip(region.otherTool(r.tool)));
            toProcess.enqueue(r.move(DOWN));
            toProcess.enqueue(r.move(RIGHT));
            if (r.at.x > 0) toProcess.enqueue(r.move(LEFT));
            if (r.at.y > 0) toProcess.enqueue(r.move(UP));

//            if (stepCount % 100000 == 0) {
//                System.out.printf("%d steps, %d queued%n", stepCount, toProcess.size());
//            }
        }
        return fastest;
    }

}

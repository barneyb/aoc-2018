package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Dir;
import com.barneyb.aoc2018.util.Point;
import com.barneyb.aoc2018.util.Queue;
import com.barneyb.aoc2018.util.TreeSet;

class Engine {

    private final Map map;
    private int rounds;

    Engine(Map map) {
        this.map = map;
    }

    void run() {
        while (doRound()) {
            rounds += 1;
        }
    }

    boolean doRound() {
        return doRound(true);
    }

    boolean doRound(boolean performAttacks) {
        for (Unit u : map.survivors()) {
            if (! u.alive()) continue; // killed earlier this round?
            if (map.isOver()) return false; // nothing to target
            Queue<Unit> enemies = new Queue<>();
            for (Unit ce : map.survivors()) {
                if (u.isGoblin() != ce.isGoblin()) enemies.enqueue(ce);
            }
            doMove(u, enemies);
            if (performAttacks) {
                doAttack(u, enemies);
            }
        }
        return true;
    }

    private void doMove(Unit u, Iterable<Unit> enemies) {
        // if adjacent to enemy, return
        for (Unit e : enemies) {
            if (u.adjacent(e)) return;
        }
        // find adjacent open points
        TreeSet<Point> candidatePoints = new TreeSet<>();
        for (Unit e : enemies) {
            addOpenAdjacent(e.location(), candidatePoints);
        }
        // if no open points, return
        if (candidatePoints.isEmpty()) return;
        // find the target to move towards
        Point targetPoint = getClosestPoint( u.location(), candidatePoints);
        // if no reachable points, return
        if (targetPoint == null) return;
        assert map.isOpen(targetPoint);
        // find the first step towards it
        TreeSet<Point> candidateSteps = getOpenAdjacent(u.location());
        Point firstStep = getClosestPoint(targetPoint, candidateSteps);
        assert firstStep != null : "we got there, but can't get back?!";
        assert firstStep.adjacent(u.location());
        assert map.isOpen(firstStep);
        map.move(u, firstStep);
    }

    private TreeSet<Point> getOpenAdjacent(Point start) {
        TreeSet<Point> col = new TreeSet<>();
        addOpenAdjacent(start, col);
        return col;
    }

    private void addOpenAdjacent(Point start, TreeSet<Point> collector) {
        for (Dir d : Dir.values()) {
            Point p = start.plus(d.delta());
            if (map.isOpen(p)) {
                collector.add(p);
            }
        }
    }

    private Point getClosestPoint(Point start, TreeSet<Point> candidates) {
        int[][] g = map.gridToPaint();
        Queue<Paint> paintQueue = new Queue<>();
        paintQueue.enqueue(new Paint(start, 0));
        while (! paintQueue.isEmpty()) {
            Paint p = paintQueue.dequeue();
            if (candidates.contains(p.point)) {
                // we found one, need to empty the queue of all other paints
                // with the same number. Dir order is insufficient at enqueue
                // time, as it only orders paints adjacent to the same point. If
                // there are multiple points with adjacent paints of the same
                // number, they'll be in an undefined order in the queue. Whee.
                Point best = p.point;
                while (! paintQueue.isEmpty()) {
                    Paint c = paintQueue.dequeue();
                    if (c.n != p.n) break;
                    if (! candidates.contains(c.point)) continue;
                    if (c.point.compareTo(best) < 0) best = c.point;
                }
                return best;
            }
            for (Point q : getOpenAdjacent(p.point)) {
                if (g[q.y][q.x] == 0) {
                    g[q.y][q.x] = p.n + 1;
                    paintQueue.enqueue(new Paint(q, p.n + 1));
                }
            }
        }
        return null; // none are reachable, i guess?
    }

    static class Paint {
        final Point point;
        final int n;

        Paint(Point point, int n) {
            this.point = point;
            this.n = n;
        }

        @Override
        public String toString() {
            return String.format("{%d,%d,%d}", n, point.x, point.y);
        }
    }

    private void doAttack(Unit u, Iterable<Unit> enemies) {
        // find all adjacent enemies
        // find one with fewest hit points
        TreeSet<Unit> candidates = new TreeSet<>();
        int minHp = Integer.MAX_VALUE;
        for (Unit e : enemies) {
            if (u.adjacent(e)) {
                if (e.hitPoints() < minHp) {
                    minHp = e.hitPoints();
                    candidates.clear();
                }
                candidates.add(e);
            }
        }
        if (candidates.isEmpty()) return;
        // break ties with reading order
        // carry out attack
        map.attack(u, candidates.min());
    }

    int rounds() {
        return rounds;
    }
}

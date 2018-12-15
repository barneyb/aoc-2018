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
        System.out.println(map.toString(true));
    }

    void run() {
        while (! map.isOver()) {
            doRound();
        }
        System.out.printf("After %d rounds%n", rounds);
        System.out.println(map.toString(true));
    }

    void doRound() {
        doRound(true);
    }

    void doRound(boolean performAttacks) {
        Iterable<Unit> allUnits = map.livingUnits();
        Queue<Unit> goblins = new Queue<>();
        Queue<Unit> elves = new Queue<>();
        for (Unit u : allUnits) {
            if (u.isGoblin()) {
                goblins.enqueue(u);
            } else {
                elves.enqueue(u);
            }
        }
        for (Unit u : allUnits) {
            if (! u.alive()) continue; // killed earlier this round?
            if (map.isOver()) return; // nothing to target
            Queue<Unit> enemies = new Queue<>();
            for (Unit ce : u.isGoblin() ? elves : goblins) {
                if (ce.alive()) enemies.enqueue(ce);
            }
            doMove(u, enemies);
            if (performAttacks) {
                doAttack(u, enemies);
            }
        }
        rounds += 1;
    }

    private void doMove(Unit u, Iterable<Unit> enemies) {
        // if adjacent to enemy, return
        for (Unit e : enemies) {
            if (u.adjacent(e)) return;
        }
        // find adjacent open points
        TreeSet<Point> candidatePoints = new TreeSet<>();
        for (Unit e : enemies) {
            Point p;
            for (Dir d : Dir.values()) {
                p = e.location().plus(d.delta());
                if (map.isOpen(p)) {
                    candidatePoints.add(p);
                }
            }
        }
        // find the target to move towards
        Point targetPoint = getTarget(u.location(), candidatePoints);
        // if no open points, return
        if (targetPoint == null) return;
        assert map.isOpen(targetPoint);
        // find the first step towards it
        TreeSet<Point> candidateSteps = new TreeSet<>();
        for (Dir d : Dir.values()) {
            Point p = u.location().plus(d.delta());
            if (map.isOpen(p)) {
                candidateSteps.add(p);
            }
        }
        Point firstStep = getTarget(targetPoint, candidateSteps);
        assert firstStep != null : "we got there, but can't get back?!";
        assert firstStep.adjacent(u.location());
        assert map.isOpen(firstStep);
        map.move(u, firstStep);
    }

    private Point getTarget(Point start, TreeSet<Point> candidates) {
        int[][] g = map.gridToPaint();
        Queue<Paint> paintQueue = new Queue<>();
        paintQueue.enqueue(new Paint(start, 0));
        while (! paintQueue.isEmpty()) {
            Paint p = paintQueue.dequeue();
            if (candidates.contains(p.point)) {
                return p.point;
            }
            for (Dir d : Dir.values()) {
                Point q = p.point.plus(d.delta());
//                if (candidates.contains(q)) {
//                    return q;
//                }
                if (map.isOpen(q) && g[q.y][q.x] == 0) {
                    g[q.y][q.x] = p.n + 1;
                    paintQueue.enqueue(new Paint(q, p.n + 1));
                }
            }
        }
        return null; // couldn't find a suitable route
    }

    static class Paint {
        final Point point;
        final int n;

        Paint(Point point, int n) {
            this.point = point;
            this.n = n;
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

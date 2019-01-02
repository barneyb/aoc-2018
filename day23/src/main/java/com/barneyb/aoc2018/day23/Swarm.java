package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.api.Collection;
import com.barneyb.aoc2018.util.*;

import java.util.Comparator;

import static com.barneyb.aoc2018.day23.Bot.STEPS;

class Swarm {

    static final Point3D ORIGIN = new Point3D(0, 0, 0);

    static Swarm parse(String input) {
        String[] lines = input.trim().split("\n");
        Bot[] bots = new Bot[lines.length];
        for (int i = 0; i < lines.length; i++) {
            bots[i] = Bot.parse(lines[i]);
        }
        return new Swarm(bots);
    }

    final Bot[] bots;
    private final Bot strongest;
    private final Point3D min;
    private final Point3D max;
    private final Point3D center;

    public Swarm(Bot[] bots) {
        this.bots = bots;
        Bot s = bots[0];
        int minx = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxy = Integer.MIN_VALUE;
        int minz = Integer.MAX_VALUE;
        int maxz = Integer.MIN_VALUE;
        for (Bot b : bots) {
            if (b.range > s.range) s = b;
            minx = Math.min(minx, b.pos.x());
            maxx = Math.max(maxx, b.pos.x());
            miny = Math.min(miny, b.pos.y());
            maxy = Math.max(maxy, b.pos.y());
            minz = Math.min(minz, b.pos.z());
            maxz = Math.max(maxz, b.pos.z());
        }
        this.strongest = s;
        min = new Point3D(minx, miny, minz);
        max = new Point3D(maxx, maxy, maxz);
        center = new Point3D(
                (min.x() + max.x()) / 2,
                (min.y() + max.y()) / 2,
                (min.z() + max.z()) / 2);
    }

    Bot strongest() {
        return strongest;
    }

    Point3D min() {
        return min;
    }

    Point3D max() {
        return max;
    }

    Point3D center() {
        return center;
    }

    Bag<Bot> canSignal(Vector p) {
        Bag<Bot> bs = new Bag<>();
        for (Bot b : bots) {
            if (b.inRange(p)) bs.add(b);
        }
        return bs;
    }

    int botCount() {
        return bots.length;
    }

    int inRangeOfStrongest() {
        Bot bot = strongest();
        int count = 0;
        for (Bot b : bots) {
            if (bot.inRange(b)) count += 1;
        }
        return count;
    }

    int distanceToIdealPoint() {
        return ORIGIN.md(idealPoint());
    }

    Vector idealPoint() {
        // first, build a graph of bots w/ a signal overlap
        Graph g = new Graph(botCount());
        for (int i = 0; i < bots.length; i++) {
            Bot a = bots[i];
            for (int j = i + 1; j < bots.length; j++) {
                Bot b = bots[j];
                if (a.overlaps(b)) {
                    g.addEdge(i, j);
                }
            }
        }
        // for small swarms, do some stats
        if (botCount() < 20) {
            Histogram<Integer> h = new Histogram<>();
            for (int i = 0; i < g.getSiteCount(); i++) {
                h.add(i, g.adjacentTo(i).size());
                System.out.print("Site " + i + ":");
                for (int j : g.adjacentTo(i)) {
                    System.out.print(" " + j);
                }
                System.out.println();
            }
            System.out.println("Sites: " + g.getSiteCount());
            System.out.println("Edges: " + g.getEdgeCount());
            System.out.println(h.toBarChart());
        }

        // find the largest set of sites which all mutually overlap
        int bestSize = -1;
        Queue<Integer> sites = new Queue<>();
        siteLoop:
        for (int s = 0; s < g.getSiteCount(); s++) {
            Collection<Integer> adjacencies = g.adjacentTo(s);
            int size = adjacencies.size();
            if (size < bestSize) continue;
            for (int i : adjacencies) {
                for (int j : adjacencies) {
                    if (i < j && ! g.adjacent(i, j)) continue siteLoop;
                }
            }
            // everything overlaps
            if (size > bestSize) {
                bestSize = size;
                sites.clear();
            }
            // only if it's part of the active "fully connected same sized set"
            if (sites.isEmpty() || g.adjacent(s, sites.peek()))
                sites.enqueue(s);
        }
        System.out.println("Best size: " + bestSize + " (at " + sites.size() + " sites):");
        System.out.println("    " + sites);

        // gather _exactly_ the bots whose ranges overlap
        int aSite = sites.peek();
        TreeSet<Integer> inRangeSites = new TreeSet<>();
        inRangeSites.add(aSite);
        for (Integer s : g.adjacentTo(aSite)) {
            inRangeSites.add(s);
        }

        System.out.println("all sites (" + inRangeSites.size() + "):");
        System.out.println("    " + inRangeSites);

        Bot a = bots[inRangeSites.min()];
        // about 1/100th of the range
        double range = a.range / 100.0;
        int stepDivisor = 10;
        int initialStep = (int) Math.pow(stepDivisor, (int) (Math.log(range) / Math.log(stepDivisor)));
        System.out.printf("Steps %,d / %d%n", initialStep, stepDivisor);
        Vector start = a.pos;
        TreeSet<Vector> intersect = null;
        Bot[] inRangeBots = new Bot[inRangeSites.size()];
        int i = 0;
        for (int s : inRangeSites) {
            inRangeBots[i++] = bots[s];
        }
        for (int s = Math.max(1, initialStep); s >= 1; s /= stepDivisor) {
            System.out.printf("Using pitch %,d...%n", s);
            intersect = intersection(start, inRangeBots, s);
            start = intersect.min();
        }
        Vector[] arr = new Vector[intersect.size()];
        i = 0;
        for (Vector v : intersect) {
            arr[i++] = v;
        }
        new Sort<Vector>(Comparator.comparingInt(ORIGIN::md)).sort(arr);

        return arr[0];
    }

    private TreeSet<Vector> intersection(Vector start, Bot[] bots, int stepSize) {
        TreeSet<Vector> points = new TreeSet<>();
        TreeSet<Vector> visited = new TreeSet<>();
        Queue<Vector> queue = new Queue<>();
        queue.enqueue(start);
        int bestCount = 2;
        Vector[] steps = new Vector[STEPS.length];
        for (int i = 0; i < steps.length; i++) {
            steps[i] = STEPS[i].times(stepSize);
        }
        while (! queue.isEmpty()) {
            Vector p = queue.dequeue();
            if (points.contains(p)) continue;
            int count = 0;
            for (Bot b : bots) {
                if (b.inRange(p)) {
                    count += 1;
                }
            }
            if (count < bestCount - 2) continue; // leave two (not one), because of the 3-d manhattan distance wobble
            if (count > bestCount) {
                bestCount = count;
                points.clear();
            }
            if (count == bestCount) {
                points.add(p);
            }
            for (Vector s : steps) {
                Vector n = p.plus(s);
                if (visited.contains(n)) continue;
                visited.add(n);
                queue.enqueue(n);
            }
        }
        System.out.printf("best: %d (of %d), points: %,d, visited: %,d%n", bestCount, bots.length, points.size(), visited.size());
        return points;
    }
}

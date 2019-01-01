package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.*;

import static com.barneyb.aoc2018.day23.Bot.STEPS;

public class Day23 extends OneShotDay {

    private static final Point3D ORIGIN = new Point3D(0, 0, 0);

    @Override
    public Answers solve(String input) {
        Swarm swarm = Swarm.parse(input);
        return new Answers(
                partOne(swarm)
                , partTwo(swarm)
        );
    }

    static int partOne(Swarm swarm) {
        Bot bot = swarm.strongest();
        int count = 0;
        for (Bot b : swarm.bots) {
            if (bot.inRange(b)) count += 1;
        }
        return count;
    }

    static int partTwo(Swarm swarm) {
        // first, build a graph of bots w/ a signal overlap
        WeightedGraph g = new WeightedGraph(swarm.botCount());
        Bot[] bots = swarm.bots;
        for (int i = 0; i < bots.length; i++) {
            Bot a = bots[i];
            for (int j = i + 1; j < bots.length; j++) {
                Bot b = bots[j];
                if (a.overlaps(b)) {
                    g.addEdge(i, j, a.overlapsBy(b));
                }
            }
        }
        // for small swarms, do some stats
        if (swarm.botCount() < 20) {
            Histogram<Integer> h = new Histogram<>();
            for (int i = 0; i < g.getSiteCount(); i++) {
                h.add(i, g.adjacentCount(i));
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
        Bag<Integer> sites = new Bag<>();
        siteLoop:
        for (int s = 0; s < g.getSiteCount(); s++) {
            int size = g.adjacentCount(s);
            if (size < bestSize) continue;
            Iterable<Integer> adjacencies = g.adjacentTo(s);
            // todo: this double-checks each adjacency
            for (int i : adjacencies) {
                for (int j : adjacencies) {
                    if (i != j && ! g.adjacent(i, j)) continue siteLoop;
                }
            }
            // everything overlaps
            if (size > bestSize) {
                bestSize = size;
                sites.clear();
            }
            // only if it's part of the active "fully connected same sized set"
            if (sites.isEmpty() || g.adjacent(s, sites.iterator().next()))
                sites.add(s);
        }
        System.out.println("Best size: " + bestSize + " (at " + sites.size() + " sites)");
        System.out.println("    " + sites);

        // gather _exactly_ the bots whose ranges overlap
        int aSite = sites.iterator().next();
        TreeSet<Integer> allSites = new TreeSet<>();
        allSites.add(aSite);
        for (Integer s : g.adjacentTo(aSite)) {
            allSites.add(s);
        }

        System.out.println("all sites (" + allSites.size() + "): " + allSites);

        // find the pairs of bots with the smallest overlap "thickness"
        Bag<Vector> bests = new Bag<>();
        int min = Integer.MAX_VALUE;
        for (int s : allSites) {
            for (int i : g.adjacentTo(s)) {
                if (i <= s) continue; // only check one direction
                if (! allSites.contains(i)) continue; // only check candidates
                int w = g.weight(s, i);
//                System.out.println("    Sites " + s + "," + i + " have overlap " + w);
                if (w < min) {
                    min = w;
                    bests.clear();
                }
                if (w == min) {
                    bests.add(new Vector(s, i));
                }
            }
//            System.out.println("  Sites " + s + "," + bestI + " have overlap " + min);
        }
        System.out.println("Site pairs with overlap " + min + " (" + bests.size() + "):");
        System.out.println("  " + bests);

        // so at this point, we've got a list of all minimally thick overlaps
        // in terms of the two bots who create it, within the set of all bots
        // that are in signal range of the solution points

        Vector best = bests.iterator().next();
        int bestS = best.dim(0), bestI = best.dim(1);

        Bot a = bots[bestS];
        System.out.println(a);
        Bot b = bots[bestI];
        System.out.println(b);
        System.out.println("  overlap: " + a.overlapsBy(b));
        Vector mid = a.midpoint(b);
        System.out.println(" midpoint: " + mid);
        System.out.println("     in a: " + a.inRange(mid) + " (" + a.pos.md(mid) + ")");
        System.out.println("     in b: " + b.inRange(mid) + " (" + b.pos.md(mid) + ")");
        System.out.println(" distance: " + mid.md(ORIGIN));

        return partTwo_b(swarm, allSites, bests);
    }

    static int partTwo_b(Swarm swarm, TreeSet<Integer> allSites, Bag<Vector> pairs) {
        // todo: this screws sites? i think?
        Bot[] bots = new Bot[allSites.size()];
        int i = 0;
        for (int s : allSites) {
            bots[i++] = swarm.bots[s];
        }
        Vector maxPair = null;
        double max = Double.MAX_VALUE;
        for (Vector pair : pairs) {
            Bot a = bots[pair.dim(0)];
            Bot b = bots[pair.dim(1)];
            int dx = Math.abs(a.pos.x() - b.pos.x());
            int dy = Math.abs(a.pos.y() - b.pos.y());
            int dz = Math.abs(a.pos.z() - b.pos.z());
            int r = a.range + b.range;
            double fx = 1.0 * dx / r;
            double fy = 1.0 * dy / r;
            double fz = 1.0 * dz / r;

            // todo: put this back?
            double fMax = Math.max(fx, Math.max(fy, fz));
            if (r < max) {
                max = r;
                maxPair = pair;
            }
        }
        assert maxPair != null;
        Bot a, b;
        TreeSet<Vector> intersect = null;

        System.out.println("Maxed:");
        a = bots[maxPair.dim(0)];
        System.out.println(a);
        b = bots[maxPair.dim(1)];
        System.out.println(b);
        Vector start = a.midpoint(b);
        for (int s = 10000; s >= 1; s /= 10) {
            intersect = intersection(start, bots, s);
            start = intersect.min();
            System.out.printf("w/ pitch %,d, intersection: %d points:%n", s, intersect.size());
            int cutoff = 0;
            for (Vector p : intersect) {
                System.out.printf("  %s : %,d%n", p, ORIGIN.md(p));
                if (++cutoff == 20) break;
            }
        }

        if (intersect.size() == 1) return ORIGIN.md(intersect.min());
        return -1;
    }

    static TreeSet<Vector> intersection(Vector start, Bot[] bots, int stepSize) {
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
                System.out.printf("new best: %d (of %d), old points: %,d%n", bestCount, bots.length, points.size());
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
        System.out.printf("queued: %,d, best: %d (of %d), points: %,d, visited: %,d%n", queue.size(), bestCount, bots.length, points.size(), visited.size());
        return points;
    }

    // 138,697,281

    public static void main(String[] args)  {
        Day23 d = new Day23();
        String input = FileUtils.readFile("day23/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %,d ms%n", a, e);
    }

}

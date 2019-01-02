package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.api.Collection;
import com.barneyb.aoc2018.util.*;

import java.util.Comparator;

import static com.barneyb.aoc2018.day23.Bot.STEPS;

public class Day23 extends OneShotDay {

    private static final Point3D ORIGIN = new Point3D(0, 0, 0);

    @Override
    public Answers solve(String input) {
        System.out.println("Day 23 takes 45-50 seconds to solve...");
        Swarm swarm = Swarm.parse(input);
        return new Answers(
                swarm.inRangeOfStrongest()
                , partTwo(swarm)
        );
    }

    static int partTwo(Swarm swarm) {
        // first, build a graph of bots w/ a signal overlap
        Graph g = new Graph(swarm.botCount());
        Bot[] bots = swarm.bots;
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
        if (swarm.botCount() < 20) {
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

        Bot[] allBots = swarm.bots;
        Bot a = allBots[inRangeSites.min()];
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
            inRangeBots[i++] = allBots[s];
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

        return ORIGIN.md(arr[0]);
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

    public static void main(String[] args)  {
        Day23 d = new Day23();
        String input = FileUtils.readFile("day23/input.txt");
        Stopwatch watch = new Stopwatch();
        Answers a = d.solve(input);
        long e = watch.stop();
        System.out.printf("%s in %,d ms%n", a, e);
    }

}

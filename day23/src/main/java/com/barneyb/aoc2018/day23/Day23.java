package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.*;

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
            sites.add(s);
        }
        System.out.println("Best size: " + bestSize + " (at " + sites.size() + " sites)");
        System.out.println("    " + sites);

        // repackage into a single collection all the bots whose ranges overlap
        int aSite = sites.iterator().next();
        TreeSet<Integer> candidates = new TreeSet<>();
        candidates.add(aSite);
        for (Integer s : g.adjacentTo(aSite)) {
            candidates.add(s);
        }

        System.out.println("now " + candidates);

        // find the pairs of bots with the smallest overlap "thickness"
        Bag<Vector> bests = new Bag<>();
        int min = Integer.MAX_VALUE;
        for (int s : candidates) {
            for (int i : g.adjacentTo(s)) {
                if (i <= s) continue; // only check one direction
                if (! candidates.contains(i)) continue; // only check candidates
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
        System.out.println("Sites with overlap " + min + ":");
        System.out.println("  " + bests);

        // so at this point, we've got a list of all minimally thick overlaps
        // in terms of the two bots who create it, within the set of all bots
        // that are in signal range of the solution points

        return -1;

//        Bot a = bots[bestS];
//        System.out.println(a);
//        Bot b = bots[bestI];
//        System.out.println(b);
//        System.out.println("  overlap: " + a.overlapsBy(b));
//
//        Bag<Vector> steps = new Bag<>(new Vector[] {
//                new Vector(1, 0, 0),
//                new Vector(-1, 0, 0),
//                new Vector(0, 1, 0),
//                new Vector(0, -1, 0),
//                new Vector(0, 0, 1),
//                new Vector(0, 0, -1),
//        });
//
//        TreeSet<Vector> visited = new TreeSet<>();
//        Queue<Vector> queue = new Queue<>();
//        queue.enqueue(a.midpoint(b));
//        System.out.println(queue);
//        int bestDist = Integer.MAX_VALUE;
//        int checks = 0, improvements = 0;
//        while (! queue.isEmpty()) {
//            checks += 1;
//            Vector v = queue.dequeue();
//            visited.add(v);
//            int d = v.md(ORIGIN);
//            if (d < bestDist) {
//                improvements += 1;
//                bestDist = d;
//            }
//
//            for (Vector s : steps) {
//                Vector n = v.plus(s);
//                if (!a.inRange(n)) continue;
//                if (!b.inRange(n)) continue;
//                if (visited.contains(n)) continue;
//                queue.enqueue(n);
//            }
//        }
//        System.out.println("checks : " + checks);
//        System.out.println("improvs: " + improvements);
//        return bestDist;
    }

    // 79182713 is too low
    // 140,176,641 is too high

    public static void main(String[] args)  {
        String input = FileUtils.readFile("day23/input.txt");
        Stopwatch watch = new Stopwatch();
        Swarm swarm = Swarm.parse(input);
        int dist = Day23.partTwo(swarm);
        long e = watch.stop();
        System.out.printf("GLERGGGGGGG: %s in %d ms%n", dist, e);
    }

}

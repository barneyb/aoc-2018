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

        // for each site
        int bestSize = -1;
        Bag<Integer> sites = new Bag<>();
        siteLoop:
        for (int s = 0; s < g.getSiteCount(); s++) {
            TreeSet<Integer> adjacencies = g.adjacentTo(s);
            int size = adjacencies.size();
            if (size < bestSize) continue;
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
        System.out.println("Best size: " + bestSize + " at " + sites.size() + " sites:");
        System.out.println("    " + sites);

        return -1;
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

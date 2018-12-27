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
        /*
                :           x           y           z     bs           d-o
      [ best    : (  56379458,   41112372,   43945533)   903     141437363 ]
        min     : (-158197890, -115956611,  -81529762)     0     355684263
        max     : ( 251687255,  175412141,  166158502)     0     593257898
        center  : (  46744682,   29727765,   42314370)   566     118786817

        because of the octahedrons, only need to check planes which have a bot
        on them. If two octahedrons overlap, they'll overlap at least one
        vertex, as well as some (possibly zero) number of edge/interior points
         */
        TreeSet<Integer> xs = new TreeSet<>();
        TreeSet<Integer> ys = new TreeSet<>();
        TreeSet<Integer> zs = new TreeSet<>();
        for (Bot b : swarm.bots) {
            xs.add(b.pos.x());
            ys.add(b.pos.y());
            zs.add(b.pos.z());
        }
        int max = 0;
        List<Vector> candidates = new List<>();
        int nx = 0;
        for (int x : xs) {
            for (int y : ys) {
                for (int z : zs) {
                    Vector p = new Vector(x, y, z);
                    int count = 0;
                    Bot[] bots = swarm.bots;
                    for (int i = 0; i < bots.length; i++) {
                        if (count + bots.length - i < max) break;
                        if (bots[i].inRange(p)) count += 1;
                    }
                    if (count > max) {
                        max = count;
                        candidates.clear();
                    }
                    if (count == max) {
                        candidates.add(p);
                    }
                }
            }
            System.out.printf("x %d complete, max is %d, w/ %d points%n", ++nx, max, candidates.size());
        }
        System.out.println("Points:");
        System.out.println(candidates.size());
        System.out.println(candidates);
        int min = Integer.MAX_VALUE;
        Vector best = null;
        for (Vector v : candidates) {
            int d = v.md(ORIGIN);
            if (d < min) {
                min = d;
                best = v;
            }
        }
        assert best != null;
        System.out.printf("%-8s:  %10s  %10s  %10s   %4s  %12s%n", "", "x", "y", "z", "bs", "d-o");
        print(swarm, "best", best);
        print(swarm, "min", swarm.min());
        print(swarm, "max", swarm.max());
        print(swarm, "center", swarm.center());
        return best.md(ORIGIN);
    }

    static void print(Swarm s, String l, Vector p) {
        System.out.printf("%-8s: (%10d, %10d, %10d)  %4d  %12d%n", l, p.dim(0), p.dim(1), p.dim(2), s.canSignal(p).size(), p.md(ORIGIN));
    }

    // 79,182,713 is too low
    // 141,437,363 is too high

    public static void main(String[] args)  {
        String input = FileUtils.readFile("day23/input.txt");
        Stopwatch watch = new Stopwatch();
        Swarm swarm = Swarm.parse(input);
        int dist = Day23.partTwo(swarm);
        long e = watch.stop();
        System.out.printf("GLERGGGGGGG: %s in %d ms%n", dist, e);
    }

}

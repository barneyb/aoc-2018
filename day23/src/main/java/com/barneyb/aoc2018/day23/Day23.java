package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
      [ best    : (  61463252,   38187549,   48857598)   850     148508399 ] vertexes
      [ best    : (  56379458,   41112372,   43945533)   903     141437363 ] bots
        min     : (-158197890, -115956611,  -81529762)     0     355684263
        max     : ( 251687255,  175412141,  166158502)     0     593257898
        center  : (  46744682,   29727765,   42314370)   566     118786817

        because of the octahedrons, only need to check planes which have a bot
        on them. If two octahedrons overlap, they'll overlap at least one
        vertex, as well as some (possibly zero) number of edge/interior points

        ... only check planes which have a vertex on them?

        ... or maybe both bots and vertexes?
         */
        final Bot[] bots = swarm.bots;
        final int botCount = bots.length;
        TreeSet<Integer> xts = new TreeSet<>();
        TreeSet<Integer> yts = new TreeSet<>();
        TreeSet<Integer> zts = new TreeSet<>();
        for (Bot b : bots) {
            // bots
            xts.add(b.pos.x());
            yts.add(b.pos.y());
            zts.add(b.pos.z());
            // vertices
            xts.add(b.pos.x() + b.range);
            xts.add(b.pos.x() - b.range);
            yts.add(b.pos.y() + b.range);
            yts.add(b.pos.y() - b.range);
            zts.add(b.pos.z() + b.range);
            zts.add(b.pos.z() - b.range);
        }
        int[] xs = toArray(xts);
        int[] ys = toArray(yts);
        int[] zs = toArray(zts);
        shuffle(xs);
        shuffle(ys);
        shuffle(zs);
        AtomicInteger max = new AtomicInteger(1);
        AtomicInteger prev = new AtomicInteger(1);
        List<Vector> candidates = new List<>();
        AtomicInteger nx = new AtomicInteger(0);
        Stopwatch watch = new Stopwatch();
        int threadCount = 5;
        Queue<Thread> threads = new Queue<>();
        TreeSet<Vector> uniquer = new TreeSet<>();
        for (int it = 0; it < threadCount; it++) {
            final int init = it;
            Thread t = new Thread(() -> {
                for (int ix = init; ix < xs.length; ix += threadCount) {
                    int x = xs[ix];
                    for (int y : ys) {
                        for (int z : zs) {
                            Vector p = new Vector(x, y, z);
                            if (uniquer.contains(p)) continue;
                            uniquer.add(p);
                            int count = 0;
                            for (int i = 0; i < botCount; i++) {
                                if (bots[i].inRange(p)) count += 1;
                                if (count + botCount - i < max.get()) break;
                            }
                            synchronized (candidates) {
                                if (count > max.get()) {
                                    max.set(count);
                                    candidates.clear();
                                }
                                if (count == max.get()) {
                                    candidates.add(p);
                                }
                            }
                        }
                    }
                    synchronized (nx) {
                        nx.incrementAndGet();
                        double factor = 1.0 * nx.get() / xs.length;
                        double elapsed = 1.0 * watch.elapsed() / 1000 / 60;
                        double total = watch.elapsed() / factor / 1000 / 60;
                        System.out.printf("%d of %d (%.1f%%) complete, max is %d, w/ %d points. %.1f min elapsed, %.1f min remaining%n",
                                nx.get(), xs.length, factor * 100, max.get(), candidates.size(), elapsed, total - elapsed);
                        if (max.get() != prev.get()) {
                            for (Vector c : candidates) {
                                System.out.printf("  %s - %d%n", c, c.md(ORIGIN));
                            }
                            prev.set(max.get());
                        }
                    }
                }
            });
            t.start();
            threads.enqueue(t);
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
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

    private static int[] toArray(TreeSet<Integer> set) {
        int[] result = new int[set.size()];
        int i = 0;
        for (int n : set) {
            result[i++] = n;
        }
        return result;
    }

    private static void shuffle(int[] a) {
        Random r = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
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

package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;

public class Day23 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Swarm swarm = Swarm.parse(input);
        return new Answers(
                swarm.inRangeOf(swarm.strongest()).size()
//                , input.trim().length()
        );
    }

    private static class Stats {
        int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE, minz = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE, maxy = Integer.MIN_VALUE, maxz = Integer.MIN_VALUE;
        int minr = Integer.MAX_VALUE, maxr = Integer.MIN_VALUE;

        int dx, dy, dz, dr;

        int dg;

        int pos_max = 0;
        int[][] pos_xy;
        int[][] pos_yz;
        int[][] pos_xz;

        Stats(Bot[] bots) {
            for (Bot b : bots) {
                minx = Math.min(minx, b.pos.x);
                maxx = Math.max(maxx, b.pos.x);
                miny = Math.min(miny, b.pos.y);
                maxy = Math.max(maxy, b.pos.y);
                minz = Math.min(minz, b.pos.z);
                maxz = Math.max(maxz, b.pos.z);
                minr = Math.min(minr, b.range);
                maxr = Math.max(maxr, b.range);
            }

            dx = maxx - minx + 1;
            dy = maxy - miny + 1;
            dz = maxz - minz + 1;
            dr = maxr - minr + 1;

            dg = Math.min(Math.max(Math.max(dx, dy), dz), 200);
            pos_xy = new int[dg][];
            pos_yz = new int[dg][];
            pos_xz = new int[dg][];

            for (int i = 0; i < dg; i++) {
                pos_xy[i] = new int[dg];
                pos_yz[i] = new int[dg];
                pos_xz[i] = new int[dg];
            }

            for (Bot b : bots) {
                int cx = (int) (1.0 * (b.pos.x - minx) / dx * dg);
                int cy = (int) (1.0 * (b.pos.y - miny) / dy * dg);
                int cz = (int) (1.0 * (b.pos.z - minz) / dz * dg);
                pos_xy[cy][cx] += 1;
                pos_yz[cz][cy] += 1;
                pos_xz[cz][cx] += 1;
                pos_max = Math.max(pos_max,
                        Math.max(pos_xy[cy][cx],
                                Math.max(pos_yz[cz][cy], pos_xz[cz][cx])));
            }
        }
    }

    public static void main(String[] args)  {
        Day23 d = new Day23();
        String input = FileUtils.readFile("day23/input.txt");

        Swarm swarm = Swarm.parse(input);
        Stats s = new Stats(swarm.bots);

        toFile("stats.txt", out -> {
            out.printf("   %12s %12s %12s%n", "min", "max", "range");
            out.printf("x  %,12d %,12d %,12d%n", s.minx, s.maxx, s.dx);
            out.printf("y  %,12d %,12d %,12d%n", s.miny, s.maxy, s.dy);
            out.printf("z  %,12d %,12d %,12d%n", s.minz, s.maxz, s.dz);
            out.printf("r  %,12d %,12d %,12d%n", s.minr, s.maxr, s.dr);
        });


        char[] rampSmall = reverse("@%#*+=-:.".toCharArray());
        char[] rampBig = reverse("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.".toCharArray());
        Function<Integer, Character> renderer = v -> {
            if (v == 0) return ' ';
            return rampSmall[(int) Math.floor(1.0 * v / s.pos_max * (rampSmall.length - 1))];
        };
        toFile("xy_position.txt", out -> {
            out.println("x-y plane");
            out.println(printPlane(s.pos_xy, renderer));
        });
        toFile("yz_position.txt", out -> {
            out.println("y-z plane");
            out.println(printPlane(s.pos_yz, renderer));
        });
        toFile("xz_position.txt", out -> {
            out.println("x-z plane");
            out.println(printPlane(s.pos_xz, renderer));
        });



//        Stopwatch watch = new Stopwatch();
//        Answers a = d.solve(input);
//        long e = watch.stop();
//        System.out.printf("%s in %d ms%n", a, e);
    }

    // reverse in place (and return)
    private static char[] reverse(char[] cs) {
        char c;
        int j;
        for (int i = 0, l = cs.length / 2; i < l; i++) {
            j = cs.length - i - 1;
            c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
        }
        return cs;
    }

    private static void toFile(String filename, Consumer<PrintStream> work) {
        try {
            FileOutputStream fs = new FileOutputStream("day23/" + filename);
            BufferedOutputStream bs = new BufferedOutputStream(fs);
            PrintStream ps = new PrintStream(bs);
            work.accept(ps);
            ps.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static String printPlane(int[][] g, Function<Integer, Character> renderer) {
        StringBuilder sb = new StringBuilder();
        int dg = g.length;
        int l = ("" + dg).length();
        sb.append(String.format("%" + l + "s  ", ""));
        for (int i = 0; i < dg; i++) {
            if (i % 10 == 0) {
                sb.append(String.format("| %-8d", i));
            }
        }
        sb.append("\n");
        for (int i = 0; i < dg; i++) {
            int[] rows = g[i];
            sb.append(String.format("%" + l + "d |", i));
            for (int cell : rows) {
                sb.append(renderer.apply(cell));
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

}

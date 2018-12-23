package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Point3D;

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
        Range xRange = Range.EMPTY;
        Range yRange = Range.EMPTY;
        Range zRange = Range.EMPTY;
        Range rRange = Range.EMPTY;
        Range gRange;

        Range pRange = new Range(0, 1);
        int[][] pos_xy;
        int[][] pos_yz;
        int[][] pos_xz;

        Stats(Bot[] bots) {
            for (Bot b : bots) {
                Point3D p = b.pos;
                xRange = xRange.plus(p.x);
                yRange = yRange.plus(p.y);
                zRange = zRange.plus(p.z);
                rRange = rRange.plus(b.range);
            }

            gRange = new Range(0,
                    Math.min(Math.max(Math.max(xRange.size(), yRange.size()), zRange.size()), 200));
            int dg = gRange.end();
            pos_xy = new int[dg][];
            pos_yz = new int[dg][];
            pos_xz = new int[dg][];

            for (int i = 0; i < dg; i++) {
                pos_xy[i] = new int[dg];
                pos_yz[i] = new int[dg];
                pos_xz[i] = new int[dg];
            }

            for (Bot b : bots) {
                Point3D p = b.pos;
                int cx = gRange.unscale(xRange.scale(p.x));
                int cy = gRange.unscale(yRange.scale(p.y));
                int cz = gRange.unscale(zRange.scale(p.z));
                pRange = pRange
                        .plus(pos_xy[cy][cx] += 1)
                        .plus(pos_yz[cz][cy] += 1)
                        .plus(pos_xz[cz][cx] += 1);
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
            out.printf("x  %,12d %,12d %,12d%n", s.xRange.start(), s.xRange.end() - 1, s.xRange.size());
            out.printf("y  %,12d %,12d %,12d%n", s.yRange.start(), s.yRange.end() - 1, s.yRange.size());
            out.printf("z  %,12d %,12d %,12d%n", s.zRange.start(), s.zRange.end() - 1, s.zRange.size());
            out.printf("r  %,12d %,12d %,12d%n", s.rRange.start(), s.rRange.end() - 1, s.rRange.size());
        });

        char[] gamut = " .:-=+*#%@".toCharArray();
        Range gamutRange = new Range(0, gamut.length);
//        char[] rampBig = reverse("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.".toCharArray());
        Function<Integer, Character> renderer = v -> {
            return gamut[gamutRange.unscale(s.pRange.scale(v))];
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

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

    public static void main(String[] args)  {
        Day23 d = new Day23();
        String input = FileUtils.readFile("day23/input.txt");

        Swarm swarm = Swarm.parse(input);
        Stats s = new Stats(swarm.bots,
                Range.inclusive(-158_197_890, 251_687_255),
                Range.inclusive(-115_956_611, 175_412_141),
                Range.inclusive(-81_529_762, 166_158_502));

        toFile("stats.txt", out -> {
            out.printf("   %12s %12s %12s%n", "min", "max", "range");
            out.printf("x  %,12d %,12d %,12d%n", s.xRange.start(), s.xRange.end() - 1, s.xRange.size());
            out.printf("y  %,12d %,12d %,12d%n", s.yRange.start(), s.yRange.end() - 1, s.yRange.size());
            out.printf("z  %,12d %,12d %,12d%n", s.zRange.start(), s.zRange.end() - 1, s.zRange.size());
            out.printf("r  %,12d %,12d %,12d%n", s.rRange.start(), s.rRange.end() - 1, s.rRange.size());
        });

        // " .:-=+*#%@"
        // " .'`^"\,:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"
        char[] gamut = " .:-=+*#%@".toCharArray();
        Range gamutRange = Range.halfOpen(0, gamut.length);
        Function<Integer, Character> renderer = v -> {
            return gamut[gamutRange.unscale(s.pRange.scale(v))];
        };
        toFile("xy_position.txt", out -> {
            out.println("x-y plane");
            out.println(printPlane(s.pos_xy, s.xRange, s.yRange, renderer));
        });
        toFile("yz_position.txt", out -> {
            out.println("y-z plane");
            //noinspection SuspiciousNameCombination
            out.println(printPlane(s.pos_yz, s.yRange, s.zRange, renderer));
        });
        toFile("xz_position.txt", out -> {
            out.println("x-z plane");
            out.println(printPlane(s.pos_xz, s.xRange, s.zRange, renderer));
        });



//        Stopwatch watch = new Stopwatch();
//        Answers a = d.solve(input);
//        long e = watch.stop();
//        System.out.printf("%s in %d ms%n", a, e);
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

    private static String printPlane(int[][] g, Range xRange, Range yRange, Function<Integer, Character> renderer) {
        StringBuilder sb = new StringBuilder();
        int dg = g.length;
        Range gRange = Range.halfOpen(0, dg);
        int l = Math.max(
                String.format("%,d", yRange.start()).length(),
                String.format("%,d", yRange.end()).length()
        );
        sb.append(String.format("%" + l + "s  ", ""));
        for (int x = 0; x < dg; x += 17) {
            sb.append(String.format("| %-,15d", xRange.unscale(gRange.scale(x))));
        }
        sb.append("\n");
        for (int y = 0; y < dg; y++) {
            sb.append(String.format("%," + l + "d |", yRange.unscale(gRange.scale(y))));
            for (int cell : g[y]) {
                sb.append(renderer.apply(cell));
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

}

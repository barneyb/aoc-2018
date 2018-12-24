package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.BiFunction;
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
//        input =
//                "pos=<10,12,12>, r=2\n" +
//                    "pos=<12,14,12>, r=2\n" +
//                    "pos=<16,12,12>, r=4\n" +
//                    "pos=<14,14,14>, r=6\n" +
//                    "pos=<50,50,50>, r=200\n" +
//                    "pos=<10,10,10>, r=5";

        Swarm swarm = Swarm.parse(input);
        Stats s = new Stats(swarm.bots
//                , Range.inclusive(0, 70)
//                , Range.inclusive(0, 70)
//                , Range.inclusive(0, 70)
//                , Range.inclusive(5, 25)
//                , Range.inclusive(10, 15)
//                , Range.inclusive(0, 17)
                , Range.inclusive(32_000_000, 35_000_000)
                , Range.inclusive(-136_000, 1_500_000)
                , Range.inclusive(-2_300_000, 3_000_000)
        );

//        final int y1 = -3_000_000;
//        final int z1 = -21_000_000;
//        final int y2 = 70_000_000;
//        final int z2 = -70_000_000;
//        int n1 = 0;
//        int n2 = 0;
//        for (int x = -158_197_890; x <= 251_687_255; x += 10_000) {
//            for (Bot b : swarm.bots) {
//                if (b.inRange(new Point3D(x, y1, z1))) n1++;
//                if (b.inRange(new Point3D(x, y2, z2))) n2++;
//            }
//        }
//        System.out.printf("n1: %d, n2: %d%n", n1, n2);

        toFile("stats.txt", out -> {
            out.printf("   %12s %12s %12s%n", "min", "max", "range");
            out.printf("x  %,12d %,12d %,12d%n", s.xRange.start(), s.xRange.end() - 1, s.xRange.size());
            out.printf("y  %,12d %,12d %,12d%n", s.yRange.start(), s.yRange.end() - 1, s.yRange.size());
            out.printf("z  %,12d %,12d %,12d%n", s.zRange.start(), s.zRange.end() - 1, s.zRange.size());
            out.printf("r  %,12d %,12d %,12d%n", s.rRange.start(), s.rRange.end() - 1, s.rRange.size());
        });

        toFile("xy_position.txt", s.pos_xy);
        toFile("yz_position.txt", s.pos_yz);
        toFile("xz_position.txt", s.pos_xz);
        toFile("xy_heatmap.txt", s.heat_xy);
        toFile("yz_heatmap.txt", s.heat_yz);
        toFile("xz_heatmap.txt", s.heat_xz);



//        Stopwatch watch = new Stopwatch();
//        Answers a = d.solve(input);
//        long e = watch.stop();
//        System.out.printf("%s in %d ms%n", a, e);
    }

    private static <T, U, R> Function<U, R> partial(BiFunction<T, U, R> f, T t) {
        return u -> f.apply(t, u);
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

    private static void toFile(String filename, Object thinger) {
        toFile(filename, out -> out.println(thinger));
    }

}

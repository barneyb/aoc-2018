package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Bounds;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Day23B extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Swarm swarm = Swarm.parse(input);
        return new Answers(
                swarm.inRangeOf(swarm.strongest()).size()
//                , input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day23B d = new Day23B();
        String input = FileUtils.readFile("day23/input.txt");
//        input =
//                "pos=<10,12,12>, r=2\n" +
//                    "pos=<12,14,12>, r=2\n" +
//                    "pos=<16,12,12>, r=4\n" +
//                    "pos=<14,14,14>, r=6\n" +
//                    "pos=<50,50,50>, r=200\n" +
//                    "pos=<10,10,10>, r=5";

        Swarm swarm = Swarm.parse(input);

//        System.out.println(swarm.max(
//                Range.inclusive(38519336, 38519337) // x: 1
//                , Range.inclusive(-20339739, -19462641) // y: 877098
//                , Range.inclusive(-20323838, -18925558) // z: 1398280
//        ));
//        if (1 == 1) return;

        Range rx = Range.inclusive(38519136, 38519537); // x: 400
        Range ry = Range.inclusive(-20339739, -20308028); // y: 31711
        Range rz = Range.inclusive(-20323838, -20273353); // z: 50485
        Stats s = new Stats(swarm.bots
//                , Range.inclusive(0, 70)
//                , Range.inclusive(0, 70)
//                , Range.inclusive(0, 70)
//                , Range.inclusive(5, 25)
//                , Range.inclusive(10, 15)
//                , Range.inclusive(0, 17)
//                , Range.inclusive(32_000_000, 35_000_000)
//                , Range.inclusive(-136_000, 1_500_000)
//                , Range.inclusive(-2_300_000, 3_000_000)
                , rx // x: 200
                , ry
                , rz
        );

        long prevSpace = (long) rx.size() * ry.size() * rz.size();
        for (int gen = 0; gen < 1000; gen++) {
//            Bounds xy = s.heat_xy.maximaBounds();
            Bounds yz = s.heat_yz.maximaBounds();
//            Bounds xz = s.heat_xz.maximaBounds();
//            rx = Range.halfOpen(xy.min().x, xy.max().x)
//                    .plus(xz.min().x)
//                    .plus(xz.max().x);
            ry = Range.halfOpen(yz.min().x, yz.max().x);
//                    .plus(yz.min().x)
//                    .plus(yz.max().x);
            rz = Range.halfOpen(yz.min().y, yz.max().y);
//                    .plus(yz.min().y)
//                    .plus(yz.max().y);

            System.out.println("Generation " + gen);
            System.out.println("Range rx = Range.inclusive(" + rx.start() + ", " + rx.end() + "); // x: " + rx.size());
            System.out.println("Range ry = Range.inclusive(" + ry.start() + ", " + ry.end() + "); // y: " + ry.size());
            System.out.println("Range rz = Range.inclusive(" + rz.start() + ", " + rz.end() + "); // z: " + rz.size());
            long space = (long) rx.size() * ry.size() * rz.size();
            System.out.printf("  space: %,d (Δ%,d)%n", space, space - prevSpace);
            prevSpace = space;

            toFile("stats.txt", s);
            toFile("yz_heatmap.txt", s.heat_yz);
            if (rx.size() == 1 || ry.size() == 1 || rz.size() == 1) break;
            s = new Stats(swarm.bots, rx, ry, rz);
        }

        toFile("stats.txt", s);

//        toFile("xy_position.txt", s.pos_xy);
//        toFile("yz_position.txt", s.pos_yz);
//        toFile("xz_position.txt", s.pos_xz);

//        toFile("xy_heatmap.txt", s.heat_xy);
        toFile("yz_heatmap.txt", s.heat_yz);
//        toFile("xz_heatmap.txt", s.heat_xz);

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

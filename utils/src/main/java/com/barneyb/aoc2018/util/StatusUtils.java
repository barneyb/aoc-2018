package com.barneyb.aoc2018.util;

public class StatusUtils {

    public static void memoryStream(int periodInMs) {
        Thread t = new Thread(() -> {
            Stopwatch watch = new Stopwatch();
            while (true) {
                try {
                    Thread.sleep(periodInMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Runtime runtime = Runtime.getRuntime();
                String units;
                double count;
                if (watch.elapsed() < 75000) {
                    units = "sec";
                    count = watch.elapsed() / 1000.0;
                } else {
                    units = "min";
                    count = watch.elapsed() / 60000.0;
                }
                System.err.printf("after %,.1f %s, %s free (%s total)%n", count, units, new Mem(runtime.freeMemory()), new Mem(runtime.totalMemory()));
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private static class Mem {
        private final long mem;

        private Mem(long mem) {
            this.mem = mem;
        }

        @Override
        public String toString() {
            long m = this.mem;
            if (m < 1024) return m + "";
            m /= 1024;
            if (m < 2048) return m + "K";
            m /= 1024;
            if (m < 2048) return m + "M";
            m /= 1024;
            return m + "G";
        }
    }

}

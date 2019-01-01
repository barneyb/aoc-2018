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
                System.err.printf("after %,.1f %s, %,dM free (%,dM total)%n", count, units, runtime.freeMemory() / 1024 / 1024, runtime.totalMemory() / 1024 / 1024);
            }
        });
        t.setDaemon(true);
        t.start();
    }

}

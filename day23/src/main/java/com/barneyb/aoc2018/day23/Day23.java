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

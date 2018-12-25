package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;
import com.barneyb.aoc2018.util.Point3D;

public class Day23 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Swarm swarm = Swarm.parse(input);
        return new Answers(
                swarm.inRangeOf(swarm.strongest()).size()
//                , input.trim().length()
        );
    }

    // 79182713 is too low

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

        Range rx = Range.inclusive(38519336, 38519336);
        Range ry = Range.inclusive(-20339739, -20323245); // y: 16494
        Range rz = Range.inclusive(-20323838, -20297606); // z: 26232
        Point3D p = swarm.max(
                rx
                , ry
                , rz
        );
        System.out.println(p);
        System.out.println(p.md(Point3D.ORIGIN));
    }

}

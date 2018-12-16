package com.barneyb.aoc2018.day16;

import com.barneyb.aoc2018.day16.op.*;
import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.List;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day16 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String[] lines = input.trim().split("\n");
        List<Sample> samples = new List<>();
        int i = 0;
        // first parse samples
        while (i < lines.length) {
            String a = lines[i++];
            if (a.length() == 0) {
                // end of samples
                break;
            }
            samples.add(Sample.parse(a, lines[i++], lines[i++]));
            i += 1; // the blank line
        }
        // this is where the sample program parser goes
        return new Answers(
                partOne(samples)
//                , input.trim().length()
        );
    }

    private int partOne(List<Sample> samples) {
        Op[] ops = {
                new addr(),
                new addi(),
                new mulr(),
                new muli(),
                new banr(),
                new bani(),
                new borr(),
                new bori(),
                new setr(),
                new seti(),
                new gtir(),
                new gtri(),
                new gtrr(),
                new eqir(),
                new eqri(),
                new eqrr(),
        };
        int sampleCount = 0;
        for (Sample s : samples) {
            int opCount = 0;
            for (Op op : ops) {
                if (s.test(op)) opCount += 1;
            }
            if (opCount >= 3) {
                sampleCount += 1;
            }
        }
        return sampleCount;
    }

    public static void main(String[] args)  {
        Day16 d = new Day16();
        String input = FileUtils.readFile("day16/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

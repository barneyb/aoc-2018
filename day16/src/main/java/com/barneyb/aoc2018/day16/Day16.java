package com.barneyb.aoc2018.day16;

import com.barneyb.aoc2018.day16.op.*;
import com.barneyb.aoc2018.util.*;

public class Day16 extends OneShotDay {

    private static final Op[] OPS = {
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

    @Override
    public Answers solve(String input) {
        return solve(input, false);
    }

    Answers solvePartOne(String input) {
        return solve(input, true);
    }

    Answers solve(String input, boolean partOneOnly) {
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
        int partOne = partOne(samples);
        if (partOneOnly) return new Answers(partOne);

        BST<Integer, Op> table = buildTable(samples);
        // this is where the sample program parser goes
        return new Answers(
                partOne
//                , input.trim().length()
        );
    }

    BST<Integer, Op> buildTable(List<Sample> samples) {
        //noinspection unchecked
        TreeSet<Op>[] map = new TreeSet[OPS.length];
        for (int i = 0; i < map.length; i++)
            map[i] = new TreeSet<>(OPS);
        // exclude anything that is illegal
        for (Sample s : samples) {
            TreeSet<Op> candidates = map[s.instruction().opcode];
            for (Op op : candidates) {
                if (! s.test(op)) candidates.delete(op);
            }
        }
        // comb out single options
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < map.length; i++) {
                TreeSet<Op> ops = map[i];
                if (ops.size() == 1) {
                    Op op = ops.min();
                    for (int j = 0; j < map.length; j++) {
                        if (i == j) continue;
                        if (map[j].contains(op)) {
                            map[j].delete(op);
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);
        // convert to table
        BST<Integer, Op> table = new BST<>();
        for (int i = 0; i < map.length; i++) {
            TreeSet<Op> ops = map[i];
            if (ops.size() != 1) {
                throw new AssertionError("Failed to unique opcode mapping. :(");
            }
            table.put(i, ops.min());
        }
        return table;
    }

    private int partOne(List<Sample> samples) {
        int sampleCount = 0;
        for (Sample s : samples) {
            int opCount = 0;
            for (Op op : OPS) {
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

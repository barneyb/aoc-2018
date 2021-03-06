package com.barneyb.aoc2018.day16;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.isa.Op;
import com.barneyb.aoc2018.util.*;

import static com.barneyb.aoc2018.isa.Op.OPS;

public class Day16 extends OneShotDay {

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
        //noinspection StatementWithEmptyBody
        while (lines[i++].length() == 0) {}
        int[] registers = new int[4];
        for  (;i < lines.length; i++) {
            Instruction in = Instruction.parse(lines[i]);
            // don't use in.op(), as this problem is about finding a mapping
            Op op = table.get(in.opcode());
            op.execute(registers, in);
        }
        return new Answers(
                partOne,
                registers[0]
        );
    }

    BST<Integer, Op> buildTable(List<Sample> samples) {
        //noinspection unchecked
        TreeSet<Op>[] map = new TreeSet[OPS.length];
        for (int i = 0; i < map.length; i++)
            map[i] = new TreeSet<>(OPS);
        // exclude anything that is illegal
        for (Sample s : samples) {
            TreeSet<Op> candidates = map[s.instruction().opcode()];
            for (Op op : candidates) {
                if (! s.test(op)) candidates.delete(op);
            }
        }
        // comb out single options
        BST<Integer, Op> table = new BST<>();
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < map.length; i++) {
                TreeSet<Op> ops = map[i];
                if (ops == null) continue;
                if (ops.size() == 1) {
                    // add it to the table
                    Op op = ops.min();
                    map[i] = null;
                    table.put(i, op);
                    // remove it from all other candidate lists
                    for (TreeSet<Op> toComb : map) {
                        if (toComb == null) continue;
                        if (toComb.contains(op)) {
                            toComb.delete(op);
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);
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

package com.barneyb.aoc2018.day07;

import com.barneyb.aoc2018.util.*;

public class Day07 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return solve(input, 60, 5);
    }

    static Answers solve(String input, int extraCost, int workerCount) {
        Prerequisite[] prereqs = parse(input);
        SDG sdg = new SDG(prereqs);
        Digraph g = sdg.graph();
        TreeSet<String> readyNames = new TreeSet<>();
        int[] marked = new int[g.getSiteCount()];
        for (int i = 0, l = g.getSiteCount(); i < l; i++) {
            if (g.indegree(i) == 0) {
                readyNames.add(sdg.getName(i));
            }
        }
        Queue<String> prereqOrder = new Queue<>();
        while (! readyNames.isEmpty()) {
            String name = readyNames.min();
            readyNames.delete(name);
            int site = sdg.getSite(name);
            prereqOrder.enqueue(name);
            for (Integer a : g.adjacentTo(site)) {
                marked[a] += 1;
                if (marked[a] == g.indegree(a)) {
                    readyNames.add(sdg.getName(a));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : prereqOrder) {
            sb.append(s);
        }
        return new Answers(
                sb.toString()
        );
    }

    static Prerequisite[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Prerequisite[] prereqs = new Prerequisite[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            String[] parts = lines[i].split(" ");
            prereqs[i] = new Prerequisite(
                    parts[1],
                    parts[7]
            );
        }
        return prereqs;
    }

    public static void main(String[] args)  {
        Day07 d = new Day07();
        String input = FileUtils.readFile("day07/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}

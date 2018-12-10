package com.barneyb.aoc2018.day07;

import com.barneyb.aoc2018.util.*;

public class Day07 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return solve(input, 60, 5);
    }

    static Answers solve(String input, int extraCost, int workerCount) {
        Prerequisite[] prereqs = parse(input);
        NamedDigraph<String> ng = new NamedDigraph<>(prereqs);
        Digraph g = ng.graph();
        TreeSet<String> startTasks = new TreeSet<>();
        for (int i = 0, l = g.getSiteCount(); i < l; i++) {
            if (g.indegree(i) == 0) {
                startTasks.add(ng.getName(i));
            }
        }
        return new Answers(
                partOne(ng, startTasks),
                partTwo(ng, startTasks, extraCost, workerCount)
        );
    }

    private static int partTwo(NamedDigraph<String> ng, TreeSet<String> startTasks, int extraCost, int workerCount) {
        Digraph g = ng.graph();
        int[] siteCosts = new int[g.getSiteCount()];
        for (int i = 0, l = g.getSiteCount(); i < l; i++) {
            siteCosts[i] = extraCost + i + 1;
        }
        String[] workerTaskName = new String[workerCount];
        int[] workerTaskComplete = new int[workerCount];
        int tickCount = 0;
        TreeSet<String> readyNames = new TreeSet<>();
        for (String s : startTasks) {
            readyNames.add(s);
        }
        int[] marked = new int[g.getSiteCount()];
        while (true) {
            for (int i = 0, l = workerTaskName.length; i < l; i++) {
                if (workerTaskName[i] == null || workerTaskComplete[i] > tickCount) {
                    continue;
                }
                for (Integer a : g.adjacentTo(ng.getSite(workerTaskName[i]))) {
                    marked[a] += 1;
                    if (marked[a] == g.indegree(a)) {
                        readyNames.add(ng.getName(a));
                    }
                }
                workerTaskName[i] = null;
                workerTaskComplete[i] = 0;
            }
            while (! readyNames.isEmpty()) {
                boolean foundOne = false;
                for (int i = 0, l = workerTaskName.length; i < l; i++) {
                    if (workerTaskName[i] == null) {
                        foundOne = true;
                        String name = readyNames.min();
                        readyNames.delete(name);
                        int site = ng.getSite(name);
                        workerTaskName[i] = name;
                        workerTaskComplete[i] = tickCount + siteCosts[site];
                        break;
                    }
                }
                if (! foundOne) {
                    break;
                }
            }

            boolean foundOne = false;
            for (String task : workerTaskName) {
                if (task != null) {
                    foundOne = true;
                }
            }
            if (!foundOne) {
                break;
            }
            tickCount += 1;
        }
        return tickCount;
    }

    private static String partOne(NamedDigraph<String> ng, TreeSet<String> startTasks) {
        TreeSet<String> readyNames = new TreeSet<>();
        for (String s : startTasks) {
            readyNames.add(s);
        }
        Queue<String> prereqOrder = new Queue<>();
        Digraph g = ng.graph();
        int[] marked = new int[g.getSiteCount()];
        while (! readyNames.isEmpty()) {
            String name = readyNames.min();
            readyNames.delete(name);
            int site = ng.getSite(name);
            prereqOrder.enqueue(name);
            for (Integer a : g.adjacentTo(site)) {
                marked[a] += 1;
                if (marked[a] == g.indegree(a)) {
                    readyNames.add(ng.getName(a));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : prereqOrder) {
            sb.append(s);
        }
        return sb.toString();
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

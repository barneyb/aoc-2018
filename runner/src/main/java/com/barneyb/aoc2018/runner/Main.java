package com.barneyb.aoc2018.runner;

import com.barneyb.aoc2018.api.Day;
import com.barneyb.aoc2018.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private boolean doSolve = true;
    private int day;
    private File inputFile;

    private Main(String[] args) {
        if (args.length == 0 || isOption("help", args[0])) {
            printHelp();
            doSolve = false;
        } else if (isOption("list", args[0])) {
            printSolverList();
            doSolve = false;
        } else if (args.length != 2) {
            throw new IllegalArgumentException("You must supply a integer day and a path to a UTF-8 encoded input file.");
        } else {
            day = Integer.parseInt(args[0]);
            inputFile = new File(args[1]);
        }
    }

    private Day createSolverForUse(int day) throws IllegalAccessException {
        Day solver;
        try {
            solver = createSolver(day);
        } catch (ClassNotFoundException | InstantiationException e) {
            throw new IllegalArgumentException("Day " + day + " doesn't have a solver available");
        }
        return solver;
    }

    private static String readInput(File f) throws IOException {
        if (! f.exists()) {
            throw new FileNotFoundException("File not found: " + f.getCanonicalPath());
        }
        return FileUtils.readFile(f);
    }

    private void printHelp() {
        System.out.println("Usage: aoc-2018.jar <day> <path/to/input.txt>");
        System.out.println("       aoc-2018.jar list");
    }

    private void printSolverList() {
        for (int i = 0; i <= 25; i++) {
            try {
                createSolver(i);
                System.out.printf("Day %2d%n", i);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                System.out.printf("// Day %2d%n", i);
            }
        }
    }

    private Day createSolver(int day) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String paddedDay = day < 10 ? ("0" + day) : ("" + day);
        //noinspection unchecked
        return (Day) Class.forName(String.format("com.barneyb.aoc2018.day%s.Day%s", paddedDay, paddedDay)).newInstance();
    }

    private boolean isOption(String option, String candidate) {
        return option.equals(candidate)
                || ("--" + option).equals(candidate)
                || ("-" + option.charAt(0)).equals(candidate);
    }

    public static void main(String[] args) {
        try {
            Main m = new Main(args);
            if (m.doSolve) {
                long startedAt = System.currentTimeMillis();
                Day solver = m.createSolverForUse(m.day);
                String input = readInput(m.inputFile);
                System.out.printf("Solving Day %d against '%s' ...%n", m.day, m.inputFile);
                solver.setInput(input);
                System.out.printf("Part One: %s%n", solver.getPartOne());
                System.out.printf("Part Two: %s%n", solver.getPartTwo());
                long elapsed = System.currentTimeMillis() - startedAt;
                System.out.printf("%d ms%n", elapsed);
            }
        } catch (Exception e) {
            System.out.println("There was an error running the solver:");
            e.printStackTrace();
            System.exit(1);
        }
    }

}

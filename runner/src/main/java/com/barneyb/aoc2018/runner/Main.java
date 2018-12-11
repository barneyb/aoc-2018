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
        } else if (isOption("all", args[0])) {
            Day[] solvers = solverArray();
            for (int i = 0; i < solvers.length; i++) {
                if (solvers[i] != null) {
                    File f = new File(String.format("day%02d/input.txt", i));
                    if (f.exists()) {
                        doSolve(i, f, solvers[i]);
                    } else {
                        System.out.printf("No input file '%s' for Day %d%n", f.getPath(), i);
                    }
                }
            }
            doSolve = false;
        } else if (args.length != 2) {
            throw new IllegalArgumentException("You must supply a integer day and a path to a UTF-8 encoded input file.");
        } else {
            day = Integer.parseInt(args[0]);
            inputFile = new File(args[1]);
        }
    }

    private static Day createSolverForUse(int day) throws IllegalAccessException {
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
        System.out.println("       aoc-2018.jar all");
    }

    private void printSolverList() {
        Day[] solvers = solverArray();
        for (int i = 0; i <= 25; i++) {
            if (solvers[i] != null) {
                System.out.printf("Day %2d%n", i);
            } else {
                System.out.printf("// Day %2d%n", i);
            }
        }
    }

    private Day[] solverArray() {
        Day[] result = new Day[26];
        for (int i = 0; i <= 25; i++) {
            try {
                result[i] = createSolver(i);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ignored) {
            }
        }
        return result;
    }

    private static Day createSolver(int day) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
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
                solveAndPrint(m.day, m.inputFile);
            }
        } catch (Exception e) {
            System.out.println("There was an error running the solver:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void solveAndPrint(int day, File inputFile) throws IllegalAccessException {
        Day solver = createSolverForUse(day);
        doSolve(day, inputFile, solver);
    }

    private static void doSolve(int day, File inputFile, Day solver) {
        long startedAt = System.currentTimeMillis();
        String input;
        try {
            input = readInput(inputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Solving Day %d against '%s' ...%n", day, inputFile);
        solver.setInput(input);
        renderPart("One", solver.getPartOne());
        renderPart("Two", solver.getPartTwo());
        long elapsed = System.currentTimeMillis() - startedAt;
        System.out.printf("%d ms%n", elapsed);
    }

    private static void renderPart(String label, String answer) {
        if (answer.contains("\n")) {
            System.out.printf("Part %s:%n%s%n", label, answer);
        } else {
            System.out.printf("Part %s: %s%n", label, answer);
        }
    }

}

# aoc-2018

I solve [Advent of Code](https://adventofcode.com/2018/) 2018 problems, using
solvers running on the JVM. Each day's solution has it's own module (cleverly
named `daynn`), should you wish to view the source. Otherwise building/running
instructions are below. But first, the sample problem used in the examples:

## Day 0: Do You Measure Up

Santa wants to know how you (well, an ASCII string) measure up.

For example:

-   `"a"` has length `1`
-   `"cat"` and `" a "` both have length `3`

How long is the input?

Sample input is available at `runner/dummy-input.txt`.

### Part Two

Santa forgot that unlike people, strings can have whitespace at the ends, If
present, any such whitespace needs to be ignored. Whitespace in the middle of
the string should be left as is (you wouldn't want your lungs removed, just
because they're full of air, right?).

For example:

-   `"a"` and `" a "` both have length `1`
-   `" a b "` has length `3`

Given this revision to the measuring rules, how long is the input?

## Building

JDK 1.8.x and Maven 3.3.x are required to build:

    $ mvn --version
    Apache Maven 3.3.9
    Java version: 1.8.0_161
    $ javac -version
    javac 1.8.0_161
    $ mvn package

## Running

Bash (any remotely recent version) is used in the examples below, but is not
required. If you don't have it available, replace `./solve.sh` with `java -jar
runner/target/runner-0.1.0-SNAPSHOT-shaded.jar` in all the examples.

After building, you can solve:

    $ ./solve.sh
    Usage: ./solve.sh <day> <path/to/input.txt>
           ./solve.sh list
    $ cat runner/dummy-input.txt
      i have spaces!
    $ ./solve.sh 0 runner/dummy-input.txt
    Part One: 17
    Part Two: 14

Days available for solving may be enumerated by passing `list` as the sole
argument. Initially, only Day 0 will be available:

    $ ./solve.sh list
    Day  0
    // Day  1
    // Day  2
    // Day  3
    ...

If the day you want isn't available, you'll just have to solve it yourself. :)

## Adding a Solver

The `start.sh` script (no Bash-free equivalent is provided) is the way to get
started on a new day's solver:

    $ ./start.sh
    Usage: ./start.sh <day>
    $ ./start.sh 1
    ...
    Day  0
    Day  1
    // Day  2
    // Day  3
    ...
    // Day 25
    Part One: 17
    Part Two: 14

It requires you to be on `master` with nothing staged and a mostly clean working
dir. It'll create a new branch, module, and skeleton implementation (of Day 0),
then exercise it. You'll be left on the new branch. Crack open
`day01/src/main/java/com/barneyb/aoc2018/day01/Day01.java` and _go, go, GO!_

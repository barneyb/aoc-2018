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
           ./solve.sh all
    $ cat runner/dummy-input.txt
      i have spaces!
    $ ./solve.sh 0 runner/dummy-input.txt
    Part One: 17
    Part Two: 14

For some problems (e.g., Day 11), AoC doesn't provide `input.txt`, just a small
snippet directly on the page. You'll need to stick it in `input.txt` yourself,
as that's the only way to get input in to the solver.

Days available for solving may be enumerated by passing `list` as the sole
argument. Initially, only Day 0 will be available:

    $ ./solve.sh list
    Day  0
    // Day  1
    // Day  2
    // Day  3
    ...

If the day you want isn't available, you'll just have to solve it yourself. :)

If you drop `input.txt` into each day's directory, you can run them all in one
batch. The solver will skip any days it can't do, as well as any days an input
file wasn't provided for. Here's how it might look after the first day:

    $ ./solve.sh all
    Solving Day 0 against 'day00/input.txt' ...
    Part One: 17
    Part Two: 14
    16 ms
    No input file 'day01/input.txt' for Day 1

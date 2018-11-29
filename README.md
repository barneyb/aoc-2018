# aoc-2018

## Requirements

- Java 1.8.x
- Maven 3.3.x

Bash (any remotely recent version) is used in the examples below, but is not
required. If you don't have it available, open `solve.sh` to see the way the
packaged JAR file should be invoked.

## Building

Maven is used to build:

    mvn package

Feel free to add `-DskipTests` if you don't care.

## Running

The `solve.sh` script is the way to run (after building):

    $ ./solve.sh 
    Usage: ./solve.sh <day> <path/to/input.txt>
           ./solve.sh list
    $ cat runner/dummy-input.txt
      i have spaces!
    $ ./solve.sh 0 runner/dummy-input.txt 
    Part One: 17
    Part Two: 14

A fake "Day 0" is provided for the demo above; it simply measures the input's
length before and after trimming.

Since not all days are necessarily available, they may be enumerated by passing
`list` as the sole argument. Initially, only the fake Day 0 will be available:

    $ ./solve.sh list
    Day  0
    // Day  1
    // Day  2
    // Day  3
    ...

If the day you want isn't available, you'll just have to solve it yourself. :)

## Adding a Solver

The `setup.sh` script is the way to get started on a new day's solver:

    $ ./setup.sh 
    Usage: ./setup.sh <day>
    $ ./setup.sh 1
    
It requires you to be on `master` with nothing staged and a "mostly" clean work
dir. It'll create a new module, seed it with implementation and test skeleton
files, touch a dummy `input.txt`. and ensure it builds cleanly. Then it'll
register the new solver with the runner (for `solve.sh` to use), commit the
whole shebang to a new branch (which you'll be left on), rebuild the runner,
and finally list the available solvers, including the new one.

The generated skeleton solves the fake "Day 0" as outlined above, which most
likely is not what you want. So crack open `Day01.java` and make it better.

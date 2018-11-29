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

The `solve.sh` script is the way to run (after building)

    $ ./solve.sh 
    Usage: ./solve.sh <day> <path/to/input.txt>
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

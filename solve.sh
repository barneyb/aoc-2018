#!/usr/bin/env bash

if [ "$1" = "" -o "$1" = "help" -o "$1" = "--help" -o "$1" = "-h" ]; then
    echo "Usage: $0 <day> <path/to/input.txt>"
    echo "       $0 list"
    echo "       $0 all"
    exit
fi

java -jar runner/target/runner-0.1.0-SNAPSHOT-shaded.jar $*

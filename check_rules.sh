#!/usr/bin/env bash
set -e

TEMP_FILE=.temp.out

cd `dirname $0`

# Rule 1: no external libs, other than IO. `java.util.Iterator` and the
# `java.util.function` package are special cases because they're language
# features (foreach loop and method references, respectively), but not in the
# `java.lang` package. `java.util.Comparator` may join them at some point.
find . -name "*.java" \
    | grep -v '/test/' \
    | xargs grep 'import' \
    | egrep -v 'com\.barneyb\.aoc2018' \
    | egrep -v 'java.(n?)io\.*' \
    | egrep -v 'java\.util\.Iterator' \
    | egrep -v 'java\.util\.function.*' \
    > ${TEMP_FILE} \
    || true # `grep` exits non-zero if nothing is found

if [ `cat ${TEMP_FILE} | wc -l` != "0" ]; then
    echo
    echo "Illegal use of JRE libs:"
    echo
    cat ${TEMP_FILE}
    echo
    rm ${TEMP_FILE}
    exit 1
fi

rm ${TEMP_FILE}

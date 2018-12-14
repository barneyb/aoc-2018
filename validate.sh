#!/usr/bin/env bash
set -e

cd `dirname $0`

if [ "$1" != "--force" -a `git status --porcelain | wc -l` != "0" ]; then
    echo "Your working copy is dirty; I refuse"
    exit 1
fi

if [ ! -f ./completion.log ]; then
    echo "No 'completion.log' found. Sorry."
    exit 1
fi

dir=runner/target
expected=${dir}/expected.log
actual=${dir}/actual.log
diff=${dir}/validate.diff

mvn clean package
cat ./completion.log | egrep -v '^[0-9]+ ms' > ${expected}
./solve.sh --all | egrep -v '^[0-9]+ ms' > ${actual}
diff ${expected} ${actual} > ${diff}

echo
shasum ${expected} ${actual}
cat ${diff}

echo
if [ -s $diff ]; then
    echo "Differences found :("
    echo
    exit 1
fi
echo "No Differences Found!"
echo

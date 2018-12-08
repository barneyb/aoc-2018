#!/usr/bin/env bash
set -e

cd `dirname $0`

if [ `git status --porcelain | wc -l` != "0" ]; then
    echo "Your working copy is dirty; I refuse"
    exit 1
fi

expected=runner/target/expected.log
actual=runner/target/actual.log

mvn clean package
cat ./completion.log | egrep -v '^[0-9]+ ms' > ${expected}
./solve.sh --all | egrep -v '^[0-9]+ ms' | tee ${actual}

echo
shasum ${expected} ${actual}
echo "Diff:"

diff ${expected} ${actual}

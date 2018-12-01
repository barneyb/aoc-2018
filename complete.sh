#!/usr/bin/env bash
set -e

cd `dirname $0`

day=$1
if [ "$day" = "" ]; then
    echo "Usage: $0 <day>"
    exit
fi

if [ `git status --porcelain | wc -l` != "0" ]; then
    echo "Your working copy is dirty; I refuse"
    exit 1
fi

if [ ${#day} -eq 1 ]; then
    day="0$day"
fi

branch=day${day}

git rebase master $branch
git checkout master
git merge --no-ff -m "Merge branch '$branch'" $branch
git branch -d $branch

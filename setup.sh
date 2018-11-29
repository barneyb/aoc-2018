#!/usr/bin/env bash
set -e

cd `dirname $0`

day=$1
if [ "$day" = "" ]; then
    echo "Usage: $0 <day>"
    exit
fi

if [ `git branch | egrep '^\*' | grep master | wc -l` != "1" ]; then
    echo "You aren't on master; I refuse"
    exit 1
fi

if [ `git status --porcelain pom.xml | wc -l` != "0" ]; then
    echo "Your root pom.xml is dirty; I refuse"
    exit 1
fi

if [ `git status --porcelain runner/pom.xml | wc -l` != "0" ]; then
    echo "Your runner/pom.xml is dirty; I refuse"
    exit 1
fi

if [ `git status --porcelain | egrep -v '^\?' | egrep "^[^ ]" | wc -l` != "0" ]; then
    echo "You have staged changes; I refuse"
    exit 1
fi

if [ ${#day} -eq 1 ]; then
    day="0$day"
fi

root="day$day"
main="$root/src/main/java/com/barneyb/aoc2018/day$day"
test="$root/src/test/java/com/barneyb/aoc2018/day$day"

if [ -d $root ]; then
    echo "Day $day is already set up"
    exit 1
fi

mkdir -p $main
mkdir -p $test

cat << EOF > ${root}/pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>parent</artifactId>
        <groupId>com.barneyb.aoc-2018</groupId>
        <version>0.1.0-SNAPSHOT</version>
        <relativePath>../parent/pom.xml</relativePath>
    </parent>
    <artifactId>day$day</artifactId>
    <dependencies>
        <dependency>
            <groupId>com.barneyb.aoc-2018</groupId>
            <artifactId>api</artifactId>
            <version>\${project.version}</version>
        </dependency>
        <dependency>
            <groupId>com.barneyb.aoc-2018</groupId>
            <artifactId>utils</artifactId>
            <version>\${project.version}</version>
        </dependency>
    </dependencies>
</project>
EOF

cat << EOF > ${main}/Day${day}.java
package com.barneyb.aoc2018.day${day};

import com.barneyb.aoc2018.api.impl.Answers;
import com.barneyb.aoc2018.api.impl.OneShotDay;
import com.barneyb.aoc2018.util.FileUtils;

public class Day${day} extends OneShotDay {

    @Override
    public Answers solve(String input) {
        return new Answers(
                input.length(),
                input.trim().length()
        );
    }

    public static void main(String[] args)  {
        Day${day} d = new Day${day}();
        String input = FileUtils.readFile("day${day}/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}
EOF

cat << EOF > ${test}/Day${day}Test.java
package com.barneyb.aoc2018.day${day};

import com.barneyb.aoc2018.api.Day;
import com.barneyb.aoc2018.api.impl.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day${day}Test {

    @Test
    public void solve() {
        Answers a = new Day${day}().solve("  cat  ");
        assertEquals("7", a.getPartOne());
        assertEquals("3", a.getPartTwo());
    }

}
EOF

echo "  i am glerg!  " > ${root}/input.txt

head -n 14 pom.xml > pom.xml.new
echo "        <module>day${day}</module>" >> pom.xml.new
tail -n +15 pom.xml >> pom.xml.new
mv pom.xml.new pom.xml

mvn package -pl day${day} -am

head -n 18 runner/pom.xml > runner/pom.xml.new
cat << EOF >> runner/pom.xml.new
        <dependency>
            <groupId>com.barneyb.aoc-2018</groupId>
            <artifactId>day${day}</artifactId>
            <version>\${project.version}</version>
        </dependency>
EOF
tail -n +19 runner/pom.xml >> runner/pom.xml.new
mv runner/pom.xml.new runner/pom.xml

git checkout -b day${day} master

git add pom.xml \
    runner/pom.xml \
    ${root}/pom.xml \
    ${main}/Day${day}.java \
    ${test}/Day${day}Test.java \

# deliberately using the raw (unpadded) value
git commit -m "skeleton for day $1"

mvn package -DskipTests -pl runner -am
./solve.sh list

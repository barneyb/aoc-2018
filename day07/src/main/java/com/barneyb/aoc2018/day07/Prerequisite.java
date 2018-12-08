package com.barneyb.aoc2018.day07;

public class Prerequisite implements NamedDigraph.Edge<String> {

    final String source;
    final String target;

    Prerequisite(String source, String target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prerequisite)) return false;

        Prerequisite prereq = (Prerequisite) o;

        if (!source.equals(prereq.source)) return false;
        return this.target.equals(prereq.target);
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + target.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return source + " is prereq of " + target;
    }

}

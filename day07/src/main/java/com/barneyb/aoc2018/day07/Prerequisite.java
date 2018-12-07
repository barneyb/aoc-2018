package com.barneyb.aoc2018.day07;

public class Prerequisite {

    final String item;
    final String prereq;

    Prerequisite(String item, String prereq) {
        this.item = item;
        this.prereq = prereq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prerequisite)) return false;

        Prerequisite prereq = (Prerequisite) o;

        if (!item.equals(prereq.item)) return false;
        return this.prereq.equals(prereq.prereq);
    }

    @Override
    public int hashCode() {
        int result = item.hashCode();
        result = 31 * result + prereq.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return item + " is prereq of " + prereq;
    }

}

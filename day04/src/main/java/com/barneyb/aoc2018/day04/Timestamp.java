package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Scanner;

public class Timestamp implements Comparable<Timestamp> {

    public static Timestamp fromString(String str) {
        Scanner s = new Scanner(str);
        return fromScanner(s);
    }

    public static Timestamp fromScanner(Scanner s) {
        return new Timestamp(
                s.readInt(),
                s.skip("-").readInt(),
                s.skip("-").readInt(),
                s.skip(" ").readInt(),
                s.skip(":").readInt()
        );
    }

    private final int year, month, day, hour, minute;

    public Timestamp(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public int compareTo(Timestamp o) {
        if (year < o.year) return -1;
        if (year > o.year) return  1;
        if (month < o.month) return -1;
        if (month > o.month) return  1;
        if (day < o.day) return -1;
        if (day > o.day) return  1;
        if (hour < o.hour) return -1;
        if (hour > o.hour) return  1;
        return Integer.compare(minute, o.minute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timestamp)) return false;
        Timestamp ts = (Timestamp) o;
        if (year != ts.year) return false;
        if (month != ts.month) return false;
        if (day != ts.day) return false;
        if (hour != ts.hour) return false;
        return minute == ts.minute;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + day;
        result = 31 * result + hour;
        result = 31 * result + minute;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
    }
}

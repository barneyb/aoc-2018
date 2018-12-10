package com.barneyb.aoc2018.day10;

public class Array {

    private final Particle[] particles;
    private String message;

    public Array(Particle[] particles) {
        this.particles = particles;
    }

    String message() {
        return message;
    }

}

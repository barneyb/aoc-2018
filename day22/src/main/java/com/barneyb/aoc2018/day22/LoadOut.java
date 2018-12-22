package com.barneyb.aoc2018.day22;

import static com.barneyb.aoc2018.day22.Type.*;

public enum LoadOut {

    TORCH(ROCKY, NARROW),
    CLIMBING_GEAR(ROCKY, WET),
    NEITHER(WET, NARROW);

    private final Type a, b;

    LoadOut(Type a, Type b) {
        this.a = a;
        this.b = b;
    }

    boolean allowed(Region r) {
        return allowed(r.type());
    }

    boolean allowed(Type type) {
        return type == a || type == b;
    }
}

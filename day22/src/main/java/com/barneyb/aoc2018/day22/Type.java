package com.barneyb.aoc2018.day22;

import static com.barneyb.aoc2018.day22.LoadOut.*;

enum Type {

    ROCKY('.', TORCH, CLIMBING_GEAR),
    WET('=', CLIMBING_GEAR, NEITHER),
    NARROW('|', TORCH, NEITHER);

    static Type forErosionLevel(int el) {
        return Type.values()[el % 3];
    }

    final char indicator;
    private final LoadOut a, b;

    Type(char indicator, LoadOut a, LoadOut b) {
        this.indicator = indicator;
        this.a = a;
        this.b = b;
    }

    int riskLevel() {
        return ordinal();
    }

    public boolean allowed(LoadOut loadOut) {
        return a == loadOut || b == loadOut;
    }

    public LoadOut otherLoadout(LoadOut loadOut) {
        return a == loadOut ? b : a;
    }
}

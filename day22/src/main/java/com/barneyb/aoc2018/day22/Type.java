package com.barneyb.aoc2018.day22;

import static com.barneyb.aoc2018.day22.Tool.*;

enum Type {

    ROCKY('.', TORCH, CLIMBING_GEAR),
    WET('=', CLIMBING_GEAR, NOTHING),
    NARROW('|', TORCH, NOTHING);

    static Type forErosionLevel(int el) {
        return Type.values()[el % 3];
    }

    final char indicator;
    private final Tool a, b;

    Type(char indicator, Tool a, Tool b) {
        this.indicator = indicator;
        this.a = a;
        this.b = b;
    }

    int riskLevel() {
        return ordinal();
    }

    public boolean allowed(Tool tool) {
        return a == tool || b == tool;
    }

    public Tool otherTool(Tool tool) {
        return a == tool ? b : a;
    }
}

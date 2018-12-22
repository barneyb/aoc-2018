package com.barneyb.aoc2018.day22;

class Region {

    private final int geologicIndex;
    private final int erosionLevel;
    private final Type type;

    Region(int geologicIndex, int erosionLevel) {
        this.geologicIndex = geologicIndex;
        this.erosionLevel = erosionLevel;
        this.type = Type.forErosionLevel(erosionLevel);
    }

    int geologicIndex() {
        return geologicIndex;
    }

    int erosionLevel() {
        return erosionLevel;
    }

    Type type() {
        return type;
    }

    int riskLevel() {
        return type.riskLevel();
    }

    public Tool otherTool(Tool tool) {
        return type.otherTool(tool);
    }

    public boolean allowed(Tool tool) {
        return type.allowed(tool);
    }
}

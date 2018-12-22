package com.barneyb.aoc2018.isa;

class Line {
    private final int index;
    private final Instruction instruction;
    private String raw;
    private String human;
    private String code;

    Line(int index, Instruction instruction) {
        this.index = index;
        this.instruction = instruction;
    }

    Line(String code) {
        this.index = -1;
        this.instruction = null;
        this.human = "";
        this.code = code;
    }

    Line(String raw, String code) {
        this.index = -1;
        this.instruction = null;
        this.raw = raw;
        this.code = code;
    }

    boolean hasInstruction() {
        return this.instruction != null;
    }

    Instruction instruction() {
        return instruction;
    }

    int index() {
        return index;
    }

    String raw() {
        return raw == null ? "" : raw;
    }

    void raw(String text) {
        if (this.raw != null) {
            throw new IllegalStateException("This line already has raw text: " + this.raw + "");
        }
        this.raw = text;
    }

    String human() {
        return human == null ? "" : human;
    }

    void human(String text) {
        if (this.human != null) {
            throw new IllegalStateException("This line already has human text: " + this.human + "");
        }
        this.human = text;
    }

    String code() {
        return code;
    }

    void code(String code) {
        if (this.code != null) {
            throw new IllegalStateException("This line already has code: " + this.code + "");
        }
        this.code = code.trim();
    }

    boolean hasCode() {
        return this.code != null;
    }

}

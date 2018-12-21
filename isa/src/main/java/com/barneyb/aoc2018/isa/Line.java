package com.barneyb.aoc2018.isa;

class Line {
    private final int index;
    private final Instruction instruction;
    private String text;
    private String code;

    Line() {
        this.index = -1;
        this.instruction = null;
    }

    Line(int index, Instruction instruction) {
        this.index = index;
        this.instruction = instruction;
    }

    Line(int index, Instruction instruction, String text) {
        this.index = index;
        this.instruction = instruction;
        this.text = text;
    }

    Line(String code) {
        this.index = -1;
        this.instruction = null;
        this.text = "";
        this.code = code;
    }

    Line(String text, String code) {
        this.index = -1;
        this.instruction = null;
        this.text = text;
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

    String text() {
        return text;
    }

    void text(String text) {
        this.text = text;
    }

    String code() {
        return code;
    }

    void code(String code) {
        this.code = code.trim();
    }

    boolean hasCode() {
        return this.code != null;
    }

}

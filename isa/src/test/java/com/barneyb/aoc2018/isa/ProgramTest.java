package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.Resources;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramTest {

    @Test
    public void strings() {
        String text = Resources.asText("input.txt");
        Program p = Program.parse(text);
        assertEquals(text, p.toString());
    }

}
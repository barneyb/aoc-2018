package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.Resources;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramTest {

    @Test
    public void strings() {
        Program p = Program.parse(Resources.asText("input.txt"));
        String s = p.toString();
        System.out.println(s);
        assertEquals(Resources.asText("formatted.txt"), s);
    }

}
package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.FileUtils;

import javax.swing.*;
import java.awt.*;

public class Viz20 {

    public static final Font FONT = Font.decode(Font.MONOSPACED).deriveFont(25.0f);

    public static void main(String[] args) {
        String input = FileUtils.readFile("day20/input.txt");
        System.out.print("Generating states... ");
        Map m = Map.parse(input);
        System.out.println("done!");

        JFrame f = new JFrame("Day 20");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextArea ta = new JTextArea(m.states.dequeue(), 200, 100);
        ta.setFont(FONT);
        ta.setEditable(false);
        Container cp = f.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(new JScrollPane(ta), BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);

        new Thread(() -> {
            for (String state : m.states) {
                ta.setText(state);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}

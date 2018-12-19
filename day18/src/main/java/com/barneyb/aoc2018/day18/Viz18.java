package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.FileUtils;

import javax.swing.*;
import java.awt.*;

public class Viz18 {

    public static final Font FONT = Font.decode(Font.MONOSPACED).deriveFont(25.0f);

    public static void main(String[] args) {
        String input = FileUtils.readFile("day18/input.txt");
        Map m = Map.parse(input);
        JFrame f = new JFrame("Day 18");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel gl = new JLabel();
        gl.setFont(FONT);
        JLabel vl = new JLabel();
        vl.setFont(FONT);
        JTextArea ta = new JTextArea(m.toString(), 50, 50);
        ta.setFont(FONT);
        Container cp = f.getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel sb = new JPanel();
        sb.setLayout(new BorderLayout());
        sb.add(gl, BorderLayout.WEST);
        sb.add(vl, BorderLayout.EAST);
        cp.add(sb, BorderLayout.SOUTH);
        cp.add(ta, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                m.tick();
                gl.setText("generation: " + m.tickCount());
                vl.setText("value: " + m.resourceValue());
                ta.setText(m.toString());
            }
        }).start();
    }

}

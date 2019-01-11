package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.Bag;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.jpixelclient.raw.Frame;
import com.barneyb.jpixelclient.raw.*;

import java.awt.*;

public class Viz18 {

    static final Color C_OPEN = Color.LIGHT_GRAY;
    static final Color C_TREES = Color.GREEN;
    static final Color C_LUMBERYARD = Color.DARK_GRAY;

    public static void main(String[] args) {
        String input = FileUtils.readFile("day18/input.txt");
        Map m = Map.parse(input);
        Scene s = new Scene();
        s.addFrame(toFrame(m));
        // just do a thousand ticks
        for (int i = 0; i < 500; i++) {
            m.tick();
            s.addFrame(toFrame(m));
        }
        new SceneView().view(s);
    }

    private static Frame toFrame(Map m) {
        int width = m.width();
        int height = m.height();
        int pitch = 500 / Math.max(width, height);
        Bag<Region> lumberyards = new Bag<>();
        Bag<Region> trees = new Bag<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                char c = m.curr(x, y);
                Region r = Region.rect(x * pitch, y * pitch, pitch, pitch);
                if (c == Map.LUMBERYARD) lumberyards.add(r);
                else if (c == Map.TREES) trees.add(r);
            }
        }
        Frame f = new Frame(
                color(C_OPEN),
                Region.rect(0, 0, pitch * width, pitch * height)
        );
        f.addElement(new Element(color(C_LUMBERYARD)));
        for (Region r : lumberyards) {
            f.addElement(new Element(r));
        }
        f.addElement(new Element(color(C_TREES)));
        for (Region r : trees) {
            f.addElement(new Element(r));
        }
        return f;
    }

    private static Command color(Color color) {
        float[] comp = color.getColorComponents(null);
        return Command.color(comp[0], comp[1], comp[2]);
    }

}

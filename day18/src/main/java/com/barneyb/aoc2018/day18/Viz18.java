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

    private final Scene scene;
    private final int width;
    private final int height;
    private final int pitch;

    Viz18(Map map) {
        width = map.width();
        height = map.height();
        pitch = 1000 / Math.max(width, height);
        scene = new Scene(new Frame(
                color(C_OPEN),
                Region.rect(0, 0, pitch * width, pitch * height)));
        scene.addFrame(toFrame(map));
        for (int i = 0; i < 300; i++) {
            map.tick();
            scene.addFrame(toFrame(map));
        }
    }

    void view() {
        new SceneView().view(scene);
    }

    private Frame toFrame(Map m) {
        Bag<Region> lumberyards = new Bag<>();
        Bag<Region> trees = new Bag<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                char c = m.curr(x, y);
                if (c == Map.OPEN) continue;
                Region r = Region.rect(x * pitch, y * pitch, pitch, pitch);
                if (c == Map.LUMBERYARD) lumberyards.add(r);
                else if (c == Map.TREES) trees.add(r);
            }
        }
        Frame f = new Frame();
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

    private Command color(Color color) {
        float[] comp = color.getColorComponents(null);
        return Command.color(comp[0], comp[1], comp[2]);
    }

    public static void main(String[] args) {
        new Viz18(Map.parse(
                FileUtils.readFile("day18/input.txt")
        )).view();
    }

}

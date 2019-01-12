package com.barneyb.aoc2018.day18;

import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.jpixelclient.Raster;
import com.barneyb.jpixelclient.raw.Frame;
import com.barneyb.jpixelclient.raw.*;

import java.awt.*;

public class Viz18 {

    static final Color C_OPEN = new Color(193, 205, 32, 163);
    static final Color C_TREES = new Color(64, 140, 44);
    static final Color C_LUMBERYARD = new Color(173, 182, 201);

    private final Scene scene;
    private final int width;
    private final int height;
    private final int pitch;

    Viz18(Map map) {
        width = map.width();
        height = map.height();
        pitch = 1000 / Math.max(width, height);
        scene = new Scene(new Frame(
                Command.color(C_OPEN),
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
        Raster r = new Raster();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                char c = m.curr(x, y);
                if (c == Map.OPEN) continue;
                if (c == Map.LUMBERYARD) r.dot(x, y, C_LUMBERYARD);
                else if (c == Map.TREES) r.dot(x, y, C_TREES);
            }
        }
        return r.asFrame(pitch);
    }

    public static void main(String[] args) {
        new Viz18(Map.parse(
                FileUtils.readFile("day18/input.txt")
        )).view();
    }

}

package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.jpixelclient.Raster;
import com.barneyb.jpixelclient.raw.Scene;
import com.barneyb.jpixelclient.raw.SceneView;

import java.awt.*;

import static com.barneyb.aoc2018.util.VizUtils.awt;

public class Viz15 {

    static final Color C_FLOOR = Color.LIGHT_GRAY;
    static final Color C_WALL = Color.DARK_GRAY;
    static final Color C_ELF = new Color(32, 162, 0);
    static final Color C_GOBLIN = new Color(186, 108, 102);

    private final Scene scene;
    private final int width;
    private final int height;
    private final int pitch;

    public Viz15(Map map) {
        width = map.width();
        height = map.height();
        pitch = 1000 / Math.max(width, height);
        Raster cavern = new Raster();
        cavern.fill(new Rectangle(0, 0, width, height), C_FLOOR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map.isWall(x, y)) cavern.dot(x, y, C_WALL);
            }
        }
        scene = new Scene(cavern.asFrame(pitch));
        Raster units = new Raster();
        for (Unit u : map.survivors()) {
            units.dot(awt(u.location()), u.isElf() ? C_ELF : C_GOBLIN);
        }
        scene.addFrame(units.asFrame(pitch));
    }

    public void view() {
        new SceneView().view(scene);
    }

    public static void main(String[] args) {
        new Viz15(Map.parse(
                FileUtils.readFile("day15/input.txt")
        )).view();
    }

}

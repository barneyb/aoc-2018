package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.jpixelclient.Raster;
import com.barneyb.jpixelclient.raw.Scene;
import com.barneyb.jpixelclient.raw.SceneView;

import java.awt.*;

public class Viz15 {

    static final Color C_FLOOR = Color.LIGHT_GRAY;
    static final Color C_WALL = Color.DARK_GRAY;

    private Raster cavern;
    private final int width;
    private final int height;
    private final int pitch;

    public Viz15(Map map) {
        width = map.width();
        height = map.height();
        pitch = 1000 / Math.max(width, height);
        cavern = new Raster();
        cavern.fill(new Rectangle(0, 0, width, height), C_FLOOR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map.isWall(x, y)) cavern.dot(x, y, C_WALL);
            }
        }
    }

    public void view() {
        Scene s = new Scene(cavern.asFrame(pitch));
        new SceneView().view(s);
    }

    public static void main(String[] args) {
        new Viz15(Map.parse(
                FileUtils.readFile("day15/input.txt")
        )).view();
    }

}

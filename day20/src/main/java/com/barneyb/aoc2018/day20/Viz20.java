package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.jpixelclient.Raster;
import com.barneyb.jpixelclient.raw.Scene;
import com.barneyb.jpixelclient.raw.SceneView;

import java.awt.*;

import static com.barneyb.aoc2018.util.VizUtils.awt;

public class Viz20 {

    public static final Color C_START = Color.RED;
    public static final Color C_ROOM = Color.LIGHT_GRAY;
    public static final Color C_WALL = Color.BLACK;

    private final Scene scene;
    private final int width;
    private final int height;
    private final int pitch;

    Viz20(Map m) {
        width = m.width();
        height = m.height();
        pitch = 1000 / Math.max(width, height);
        Raster r = new Raster();
        Point origin = awt(m.origin());
        r.fill(new Rectangle(
                origin.x - 1,
                origin.y - 1,
                width + 2,
                height + 2
        ), C_WALL);
        m.doors().forEach(pt -> {
            Point p = awt(pt);
            if (p.x % 2 == 0) {
                // vertical
                r.fill(new Rectangle(p.x, p.y - 1, 1, 3), C_ROOM);
            } else {
                r.fill(new Rectangle(p.x - 1, p.y, 3, 1), C_ROOM);
            }
        });
        r.dot(0, 0, C_START);
        scene = new Scene(r.asFrame(origin, pitch));
    }

    void view() {
        new SceneView().view(scene);
    }

    public static void main(String[] args) {
        String input = FileUtils.readFile("day20/input.txt");
        new Viz20(Map.parse(input)).view();
    }

}

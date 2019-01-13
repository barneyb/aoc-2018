package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.Dir;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.jpixelclient.Raster;
import com.barneyb.jpixelclient.raw.Scene;
import com.barneyb.jpixelclient.raw.SceneView;

import java.awt.*;

import static com.barneyb.aoc2018.util.VizUtils.awt;
import static com.barneyb.jpixelclient.ColorUtils.scale;

public class Viz20 {

    public static final Color C_START = Color.RED;
    public static final Color C_FARTHEST = Color.RED;
    public static final Color C_WALL = Color.BLACK;
    public static final Color C_LO = new Color(0, 0, 255);
    public static final Color C_HI = new Color(0, 255, 0);

    private final Scene scene;

    Viz20(Map m) {
        int width = m.width();
        int height = m.height();
        int pitch = 1000 / Math.max(width, height);
        Raster r = new Raster();
        Point origin = awt(m.origin());
        r.fill(new Rectangle(
                origin.x - 1,
                origin.y - 1,
                width + 2,
                height + 2
        ), C_WALL);
        float maxD = m.distanceTo(m.farthestPoint());
        m.rooms().forEach(pt -> {
            Point p = awt(pt);
            float d = m.distanceTo(pt);
            r.fill(new Rectangle(p, new Dimension(1, 1)),
                    scale(d / maxD, C_LO, C_HI));
        });
        m.doors().forEach(pt -> {
            Point p = awt(pt);
            float d = m.distanceTo(p.x % 2 == 0
                    ? pt.go(Dir.UP)
                    : pt.go(Dir.LEFT));
            r.fill(new Rectangle(p, new Dimension(1, 1)),
                    scale(d / maxD, C_LO, C_HI));
        });
        r.dot(0, 0, C_START);
        r.dot(awt(m.farthestPoint()), C_FARTHEST);
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

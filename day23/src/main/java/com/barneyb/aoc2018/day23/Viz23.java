package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.jpixelclient.Raster;
import com.barneyb.jpixelclient.raw.Scene;
import com.barneyb.jpixelclient.raw.SceneView;

import java.awt.*;

public class Viz23 {

    private final Scene scene;

    public Viz23(Swarm swarm) {
        int factor = 1_000_000;
        scene = new Scene();
        Raster xy = new Raster();
        Raster xz = new Raster();
        Raster zy = new Raster();
        for (Bot b : swarm.bots) {
            xy.dot(b.pos.x() / factor, b.pos.y() / factor, Color.BLACK);
            zy.dot(b.pos.z() / factor, b.pos.y() / factor, Color.BLACK);
            xz.dot(b.pos.x() / factor, b.pos.z() / factor, Color.BLACK);
        }
        xy.outline(Color.RED);
        zy.outline(Color.MAGENTA);
        xz.outline(Color.YELLOW);
        Dimension d = xy.dimension();
        xy.paste(xz, new Point(0, d.height + 10));
        xy.paste(zy, new Point(d.width + 10, 0));
        scene.addFrame(xy.asFrame(xy.min(), 1));
    }

    private void view() {
        new SceneView().view(scene);
    }

    public static void main(String[] args)  {
        String input = FileUtils.readFile("day23/input.txt");
        Swarm swarm = Swarm.parse(input);
        new Viz23(swarm).view();
    }

}

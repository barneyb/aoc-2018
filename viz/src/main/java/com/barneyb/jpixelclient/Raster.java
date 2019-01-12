package com.barneyb.jpixelclient;

import com.barneyb.jpixelclient.raw.Command;
import com.barneyb.jpixelclient.raw.Frame;
import com.barneyb.jpixelclient.raw.Region;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Raster {

    private java.util.Map<Point, Color> data;

    public Raster() {
        this.data = new HashMap<>();
    }

    public void dot(int x, int y, Color c) {
        dot(new Point(x, y), c);
    }

    public void dot(Point p, Color c) {
        data.put(p, c);
    }

    public Frame asFrame(int pitch) {
        return asFrame(new Point(0, 0), pitch);
    }

    public Frame asFrame(Point origin, int pitch) {
        // group by color first...
        HashMap<Color, List<Point>> colors = new HashMap<>();
        for (Point p : data.keySet()) {
            Color c = data.get(p);
            if (! colors.containsKey(c)) {
                colors.put(c, new LinkedList<>());
            }
            colors.get(c).add(p);
        }
        // now dot!
        Frame f = new Frame();
        for (Color c : colors.keySet()) {
            f.addCommand(Command.color(c));
            for (Point p : colors.get(c)) {
                f.addRegion(Region.rect(
                        origin.x + p.x * pitch,
                        origin.y + p.y * pitch,
                        pitch,
                        pitch));
            }
        }
        return f;
    }
}

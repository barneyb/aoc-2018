package com.barneyb.jpixelclient;

import com.barneyb.jpixelclient.raw.Command;
import com.barneyb.jpixelclient.raw.Frame;
import com.barneyb.jpixelclient.raw.Region;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Raster {

    private java.util.Map<Point, Color> data = new LinkedHashMap<>();
    private java.util.Map<Rectangle, Color> rects = new LinkedHashMap<>();
    private int minX = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxY = Integer.MIN_VALUE;

    public void dot(int x, int y, Color c) {
        dot(new Point(x, y), c);
    }

    public void dot(Point p, Color c) {
        data.put(p, c);
        if (p.x < minX) minX = p.x;
        if (p.x > maxX) maxX = p.x;
        if (p.y < minY) minY = p.y;
        if (p.y > maxY) maxY = p.y;
    }

    public Point min() {
        return new Point(minX, minY);
    }

    public Point max() {
        return new Point(maxX, maxY);
    }

    public Dimension dimension() {
        return new Dimension(maxX - minX, maxY - minY);
    }

    public Rectangle bounds() {
        return new Rectangle(min(), dimension());
    }

    public void outline(Color c) {
        Rectangle b = bounds();
        rect(new Rectangle(b.x - 1, b.y - 1, b.width + 2, b.height + 2), c);
    }

    public void rect(Rectangle r, Color c) {
        rects.put(r, c);
    }

    public void paste(Raster raster) {
        paste(raster, new Point(0, 0));
    }

    public void paste(Raster raster, Point at) {
        raster.data.forEach((p, c) -> dot(new Point(
                p.x + at.x,
                p.y + at.y), c));
        raster.rects.forEach((r, c) -> rect(new Rectangle(
                r.x + at.x,
                r.y + at.y,
                r.width,
                r.height), c));
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
                        (p.x - origin.x) * pitch,
                        (p.y - origin.y) * pitch,
                        pitch,
                        pitch));
            }
        }
        for (Rectangle r : rects.keySet()) {
            f.addCommand(Command.color(rects.get(r)));
            f.addCommand(Command.thickness(1));
            Rectangle nr = new Rectangle(
                    r.x - origin.x,
                    r.y - origin.y,
                    r.width,
                    r.height);
            f.addRegion(Region.rect(nr));
        }
        return f;
    }
}

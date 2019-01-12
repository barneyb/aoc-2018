package com.barneyb.jpixelclient;

import com.barneyb.jpixelclient.raw.Command;
import com.barneyb.jpixelclient.raw.Frame;
import com.barneyb.jpixelclient.raw.Region;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Raster {

    private static abstract class Paint {
        static final int SOLID = 0;

        final Color color;
        final int thickness;

        Paint(Color color, int thickness) {
            this.color = color;
            this.thickness = thickness;
        }

        abstract Paint translate(Point p);

        abstract Region toRegion(int pitch);

        abstract Rectangle getBounds();
    }

    private static class DotPaint extends Paint {

        final Point p;

        DotPaint(Point p, Color color) {
            super(color, 0);
            this.p = p;
        }

        @Override
        Paint translate(Point by) {
            return new DotPaint(new Point(
                    p.x + by.x,
                    p.y + by.y
            ), color);
        }

        @Override
        Region toRegion(int pitch) {
            return Region.rect(
                    p.x * pitch,
                    p.y * pitch,
                    pitch,
                    pitch);
        }

        @Override
        Rectangle getBounds() {
            return new Rectangle(p.x, p.y, 1, 1);
        }
    }

    private static class RectPaint extends Paint {

        final Rectangle r;

        RectPaint(Rectangle r, Color color, int thickness) {
            super(color, thickness);
            this.r = r;
        }

        @Override
        Paint translate(Point by) {
            return new RectPaint(new Rectangle(
                    r.x + by.x,
                    r.y + by.y,
                    r.width,
                    r.height
            ), color, thickness);
        }

        @Override
        Region toRegion(int pitch) {
            return Region.rect(new Rectangle(
                    r.x * pitch,
                    r.y * pitch,
                    r.width * pitch,
                    r.height * pitch));
        }

        @Override
        Rectangle getBounds() {
            return r;
        }

    }

    private List<Paint> paints = new LinkedList<>();
    private int minX = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxY = Integer.MIN_VALUE;

    public void dot(int x, int y, Color c) {
        dot(new Point(x, y), c);
    }

    public void dot(Point p, Color c) {
        paints.add(new DotPaint(p, c));
        updateBounds(p.x, p.y, p.x, p.y);
    }

    private void updateBounds(Rectangle b) {
        updateBounds(b.x, b.y, b.x + b.width, b.y + b.height);
    }

    private void updateBounds(int x1, int y1, int x2, int y2) {
        if (x1 < minX) minX = x1;
        if (x2 > maxX) maxX = x2;
        if (y1 < minY) minY = y1;
        if (y2 > maxY) maxY = y2;
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
        rect(r, c, 1);
    }

    public void rect(Rectangle r, Color c, int strokeWidth) {
        paints.add(new RectPaint(r, c, strokeWidth));
        updateBounds(r);
    }

    public void paste(Raster raster) {
        paste(raster, new Point(0, 0));
    }

    public void paste(Raster raster, Point at) {
        raster.paints.forEach(p -> {
            p = p.translate(at);
            paints.add(p);
            updateBounds(p.getBounds());
        });
    }

    public Frame asFrame(int pitch) {
        return asFrame(new Point(0, 0), pitch);
    }

    public Frame asFrame(Point origin, int pitch) {
        origin = new Point(
                -origin.x,
                -origin.y
        );
        Frame f = new Frame();
        Color lastColor = null;
        int lastThickness = -1;
        for (Paint p : paints) {
            p = p.translate(origin);
            if (p.color != lastColor) {
                f.addCommand(Command.color(p.color));
                lastColor = p.color;
            }
            if (p.thickness != lastThickness) {
                f.addCommand(Command.thickness(p.thickness));
                lastThickness = p.thickness;
            }
            f.addRegion(p.toRegion(pitch));
        }
        return f;
    }
}

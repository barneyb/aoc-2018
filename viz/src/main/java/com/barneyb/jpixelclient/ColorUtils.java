package com.barneyb.jpixelclient;

import java.awt.*;

public class ColorUtils {

    public static Color scale(float factor, Color zero, Color one) {
        return new Color(
                (int) (zero.getRed() - (zero.getRed() - one.getRed()) * factor),
                (int) (zero.getGreen() - (zero.getGreen() - one.getGreen()) * factor),
                (int) (zero.getBlue() - (zero.getBlue() - one.getBlue()) * factor)
        );
    }

}

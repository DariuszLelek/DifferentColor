package com.omikronsoft.differentcolor.control.color;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class ColorPair {
    private final int color, differentColor;

    private ColorPair(int color, int differentColor) {
        this.color = color;
        this.differentColor = differentColor;
    }

    public static ColorPair getByDifficulty(int difficulty) {
        int color = ColorGenerator.getRandomColor();
        int differentColor = ColorGenerator.getDifferentColor(color, difficulty);
        return new ColorPair(color, differentColor);
    }

    public int getColor() {
        return color;
    }

    public int getDifferentColor() {
        return differentColor;
    }
}

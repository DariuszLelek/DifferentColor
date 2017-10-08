package com.omikronsoft.differentcolor.control.model;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class ColorGenerator {
    private static final int UPPER_LIMIT = 256;

    public static int getRandomColor(){
        Random random = new Random();
        int red = random.nextInt(UPPER_LIMIT);
        int green = random.nextInt(UPPER_LIMIT);
        int blue = random.nextInt(UPPER_LIMIT);
        return Color.rgb(red,  green, blue);
    }

    public static int getDifferentColor(int color, int difference) {
        DiffByChannel dbc = getDiffByChannel(difference);

        int red = getChannelValue(Color.red(color), dbc.getRed());
        int green = getChannelValue(Color.green(color), dbc.getGreen());
        int blue = getChannelValue(Color.blue(color), dbc.getBlue());

        return Color.rgb(red, green, blue);
    }

    private static DiffByChannel getDiffByChannel(int difference){
        Random random = new Random();
        int first = random.nextInt(difference);
        int second = Math.max(random.nextInt(difference - first), 0);
        int third = Math.max(difference - (first + second), 0);

        return new DiffByChannel(first, second, third);
    }

    private static int getChannelValue(int channel, int channelDiff){
        return channel + channelDiff >= UPPER_LIMIT ? channel - channelDiff : channel + channelDiff;
    }

    private static class DiffByChannel {
        private int red;
        private int green;
        private int blue;

        DiffByChannel(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        int getRed() {
            return red;
        }

        int getGreen() {
            return green;
        }

        int getBlue() {
            return blue;
        }
    }
}

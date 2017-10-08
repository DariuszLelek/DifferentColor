package com.omikronsoft.differentcolor.control.color;

import com.omikronsoft.differentcolor.R;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public enum LevelColor {
    LEVEL_UNKNOWN(0, R.color.level_unknown),

    LEVEL_1(1, R.color.level_1),
    LEVEL_2(2, R.color.level_2),
    LEVEL_3(3, R.color.level_3);

    private final int level;
    private final int color;

    LevelColor(int level, int color) {
        this.level = level;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public static LevelColor getByValue(int value) {
        for (LevelColor lc : values()) {
            if (lc.level == value) {
                return lc;
            }
        }
        return LEVEL_UNKNOWN;
    }

}

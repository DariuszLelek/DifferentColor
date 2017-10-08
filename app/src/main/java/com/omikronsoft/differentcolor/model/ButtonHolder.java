package com.omikronsoft.differentcolor.model;

import android.widget.Button;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class ButtonHolder {
    private final Button button;
    private final int color;
    private final boolean different;

    public ButtonHolder(Button button, int color, boolean different) {
        this.button = button;
        this.color = color;
        this.different = different;
    }

    public Button getButton() {
        return button;
    }

    public int getColor() {
        return color;
    }

    public boolean isDifferent() {
        return different;
    }
}

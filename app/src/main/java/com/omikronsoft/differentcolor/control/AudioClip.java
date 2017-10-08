package com.omikronsoft.differentcolor.control;

import com.omikronsoft.differentcolor.R;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public enum AudioClip {
    SCORE_UP(R.raw.score_up),
    GAME_OVER(R.raw.game_over),
    LIFE_LOST(R.raw.life_lost);

    private final int raw;

    AudioClip(int raw) {
        this.raw = raw;
    }

    public int getRaw() {
        return raw;
    }
}

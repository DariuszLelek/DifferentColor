package com.omikronsoft.differentcolor.control;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public interface GameControl {
    void startNewGame();

    void processButtonClick(int buttonIdx);

    void nextLevel();

    int getScore();

    boolean isGameOver();
}

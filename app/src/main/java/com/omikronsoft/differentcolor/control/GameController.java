package com.omikronsoft.differentcolor.control;

import android.widget.Button;

import com.omikronsoft.differentcolor.control.color.ColorPair;

import java.util.Random;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class GameController implements GameControl {
    private final Button[] colorButtons;
    private final GameState gameState;

    private int differentColorButtonIdx = -1;

    public GameController(Button[] colorButtons, GameState gameState) {
        this.gameState = gameState;
        this.colorButtons = colorButtons;
    }

    @Override
    public boolean rightButtonGuessProcessed(int buttonIdx) {
        if (correctButtonGuess(buttonIdx)) {
            gameState.incrementScore();
            return true;
        } else {
            gameState.decrementLives();
            return false;
        }
    }

    @Override
    public void startNewGame() {
        gameState.reset();
        nextLevel();
    }

    @Override
    public int getScore() {
        return gameState.getScore();
    }

    @Override
    public boolean isGameOver() {
        return gameState.getLives() <= 0;
    }

    @Override
    public void nextLevel() {
        gameState.increaseDifficulty();
        setButtonsColor(getNewColorPair());
    }

    private boolean correctButtonGuess(int buttonIdx) {
        return buttonIdx == differentColorButtonIdx;
    }

    private void setButtonsColor(ColorPair colorPair) {
        int defaultColor = colorPair.getColor();
        int differentColor = colorPair.getDifferentColor();

        setDefaultColorOnButtons(defaultColor);
        setDifferentColorOnButton(differentColor);
    }

    private void setDefaultColorOnButtons(int defaultColor) {
        for (Button button : colorButtons) {
            button.setBackgroundColor(defaultColor);
        }
    }

    private void setDifferentColorOnButton(int differentColor) {
        differentColorButtonIdx = new Random().nextInt(colorButtons.length);
        colorButtons[differentColorButtonIdx].setBackgroundColor(differentColor);
    }

    private ColorPair getNewColorPair() {
        return ColorPair.getByDifficulty(gameState.getDifference());
    }

}

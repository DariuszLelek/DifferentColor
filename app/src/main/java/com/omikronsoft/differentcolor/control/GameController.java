package com.omikronsoft.differentcolor.control;

import android.widget.Button;
import android.widget.TextView;

import com.omikronsoft.differentcolor.control.model.ColorPair;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class GameController implements GameControl{
    private final Button[] colorButtons;
    private final TextView livesView, scoreView;
    private final GameState gameState = new GameState();

    private ColorPair colorPair;
    private int differentColorButtonIdx = -1;

    public GameController(Button[] colorButtons, TextView livesView, TextView scoreView) {
        this.colorButtons = colorButtons;
        this.livesView = livesView;
        this.scoreView = scoreView;
    }

    @Override
    public void processButtonClick(int buttonIdx){
        if(correctButtonGuess(buttonIdx)){
            gameState.incrementScore();
        }else{
            gameState.decrementLives();
        }

        nextLevel();
    }

    @Override
    public void processTimeOut(){

    }

    @Override
    public void startNewGame(){
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

    private boolean correctButtonGuess(int buttonIdx){
        return buttonIdx == differentColorButtonIdx;
    }

    private void nextLevel(){
        setButtonsColor(getNewColorPair());

        gameState.decrementDifference();
        livesView.setText(String.format(Locale.ENGLISH, "%d", gameState.getLives()));
        scoreView.setText(String.format(Locale.ENGLISH, "%d", gameState.getScore()));
    }

    private void setButtonsColor(ColorPair colorPair){
        int defaultColor = colorPair.getColor();
        int differentColor = colorPair.getDifferentColor();

        setDefaultColorOnButtons(defaultColor);
        setDifferentColorOnButton(differentColor);
    }

    private void setDefaultColorOnButtons(int defaultColor){
        for(Button button : colorButtons){
            button.setBackgroundColor(defaultColor);
        }
    }

    private void setDifferentColorOnButton(int differentColor){
        differentColorButtonIdx = new Random().nextInt(colorButtons.length);
        colorButtons[differentColorButtonIdx].setBackgroundColor(differentColor);
    }

    private ColorPair getNewColorPair(){
        colorPair = ColorPair.getByDifficulty(gameState.getDifference());
        return colorPair;
    }

}

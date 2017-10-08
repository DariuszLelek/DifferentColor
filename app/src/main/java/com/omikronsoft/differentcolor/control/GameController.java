package com.omikronsoft.differentcolor.control;

import android.widget.Button;
import android.widget.TextView;

import com.omikronsoft.differentcolor.control.model.ColorPair;

import java.util.Random;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class GameController {
    private final Button[] buttons;
    private final TextView livesView, scoreView;
    private final GameState gameState = new GameState();

    private ColorPair colorPair;

    public GameController(Button[] buttons, TextView livesView, TextView scoreView) {
        this.buttons = buttons;
        this.livesView = livesView;
        this.scoreView = scoreView;
    }

    public void processButtonClick(int buttonIdx){

    }

    public void processTimeOut(){

    }

    public void newGame(){
        gameState.reset();
        nextLevel();
    }

    private void nextLevel(){
        setButtonsColor(getNewColorPair());

        livesView.setText(gameState.getLives());
        scoreView.setText(gameState.getScore());
    }

    private void setButtonsColor(ColorPair colorPair){
        int defaultColor = colorPair.getColor();
        int differentColor = colorPair.getDifferentColor();

        setDefaultColorOnButtons(defaultColor);
        setDifferentColorOnButton(differentColor);
    }

    private void setDefaultColorOnButtons(int defaultColor){
        for(Button button : buttons){
            button.setBackgroundColor(defaultColor);
        }
    }

    private void setDifferentColorOnButton(int differentColor){
        int buttonIdx = new Random().nextInt(buttons.length);
        buttons[buttonIdx].setBackgroundColor(differentColor);
    }

    private ColorPair getNewColorPair(){
        colorPair = ColorPair.getByDifficulty(gameState.getDifficulty());
        return colorPair;
    }

}

package com.omikronsoft.differentcolor.control;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class GameState {
    private final int MAX_LIVES = 3;
    private final int START_SCORE = 0;
    private final int START_DIFFERENCE = 80;

    private int lives = MAX_LIVES;
    private int score = START_SCORE;
    private int difference = START_DIFFERENCE;

    public void reset(){
        lives = MAX_LIVES;
        score = START_SCORE;
        difference = START_DIFFERENCE;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getDifference() {
        return difference;
    }

    public void incrementScore(){
        score ++;
    }

    public void decrementLives(){
        lives --;
    }

    public void decrementDifference(){
        difference = difference - 1 < 1 ? 1 : difference - 1;
    }
}

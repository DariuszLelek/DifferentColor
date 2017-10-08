package com.omikronsoft.differentcolor.control;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class GameState {
    private final int MAX_LIVES = 3;
    private final int START_SCORE = 0;
    private final int START_DIFFICULTY = 40;

    private int lives = MAX_LIVES;
    private int score = START_SCORE;
    private int difficulty = START_DIFFICULTY;

    public void reset(){
        lives = MAX_LIVES;
        score = START_SCORE;
        difficulty = START_DIFFICULTY;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void incrementScore(){
        score ++;
    }

    public void decrementLives(){
        lives --;
    }

    public void increaseDifficulty(){
        difficulty ++;
    }
}

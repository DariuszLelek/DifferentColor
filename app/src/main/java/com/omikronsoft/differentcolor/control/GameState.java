package com.omikronsoft.differentcolor.control;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class GameState {
    private static final int START_LEVEL = 1;
    private static final int MAX_LIVES = 3;
    private static final int MAX_LEVEL = 3;
    private static final int START_SCORE = 0;
    private static final int START_DIFFERENCE = 75;
    private static final int START_TIME = 3 * 1000;
    private static final int MIN_TIME = 500;
    private static final int THRESHOLD = 15;
    private static final int TIME_DIFF = (START_TIME - MIN_TIME) / START_DIFFERENCE;

    private int level, lives, score, difference, time;

    public GameState() {
        reset();
    }

    void reset() {
        time = START_TIME;
        level = START_LEVEL;
        lives = MAX_LIVES;
        score = START_SCORE;
        difference = START_DIFFERENCE;
    }

    public int getLevel() {
        return level;
    }

    public int getTime() {
        return time;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    int getDifference() {
        return difference;
    }

    void incrementScore() {
        score++;
    }

    void decrementLives() {
        lives--;
    }

    void increaseDifficulty() {
        updateLevel();

        difference = getDecrementedDifference();
        time = getDecrementTime();
    }

    private void updateLevel() {
        if (score > 0 && score % THRESHOLD == 0) {
            level = getIncrementedLevel();
        }
    }

    private int getIncrementedLevel() {
        int incremented = level + 1;
        return incremented > MAX_LEVEL ? MAX_LEVEL : incremented;
    }

    private int getDecrementTime() {
        int decremented = time - TIME_DIFF;
        return decremented < MIN_TIME ? MIN_TIME : decremented;
    }

    private int getDecrementedDifference() {
        int decremented = difference - 1;
        return decremented < 1 ? 1 : decremented;
    }
}

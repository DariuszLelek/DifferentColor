package com.omikronsoft.differentcolor.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.omikronsoft.differentcolor.R;
import com.omikronsoft.differentcolor.control.AudioClip;
import com.omikronsoft.differentcolor.control.AudioController;
import com.omikronsoft.differentcolor.control.GameControl;
import com.omikronsoft.differentcolor.control.GameController;
import com.omikronsoft.differentcolor.control.GameState;
import com.omikronsoft.differentcolor.control.color.LevelColor;

import java.util.Locale;

public class GameBoard extends AppCompatActivity {
    private static final int GAME_OVER_SLEEP_DELAY = 500;
    private static final int PROGRESS_BAR_UPDATE_DELAY = 25;

    private SharedPreferences prefs;
    private GameControl gameController;
    private int time;
    private boolean timerRunning = false;
    private TextView livesView, scoreView;
    private GameState gameState;
    private ProgressBar progressBar;
    private boolean soundEnabled, gameEnded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        prepareHomeButton();

        prefs = this.getSharedPreferences("DifferentColor", Context.MODE_PRIVATE);
        soundEnabled = prefs.getBoolean(getString(R.string.sound_enabled_key), true);
        gameEnded = false;

        Button[] colorButtons = getColorButtons();
        gameState = new GameState();
        livesView = (TextView) findViewById(R.id.text_lives);
        scoreView = (TextView) findViewById(R.id.text_score);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        gameController = getGameController(colorButtons, gameState);
        gameController.startNewGame();
    }

    private void prepareHomeButton() {
        ImageButton buttonHome = (ImageButton) findViewById(R.id.button_home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame();
            }
        });
    }

    private Button[] getColorButtons() {
        Button[] buttons = new Button[4];

        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);

        setOnClickListeners(buttons);

        return buttons;
    }

    private void setOnClickListeners(Button[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            View.OnClickListener listener = getColorButtonListener(i);
            buttons[i].setOnClickListener(listener);
        }
    }

    private GameControl getGameController(Button[] colorButtons, GameState gameState) {
        return new GameController(colorButtons, gameState);
    }

    private View.OnClickListener getColorButtonListener(final int buttonIdx) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameController.rightButtonGuessProcessed(buttonIdx)){
                    AudioController.play(getApplicationContext(), AudioClip.SCORE_UP, soundEnabled);
                }else{
                    AudioController.play(getApplicationContext(), AudioClip.LIFE_LOST, soundEnabled);
                }
                processGameAction();
            }
        };
    }

    private void updateViews() {
        livesView.setText(getLivesString());
        scoreView.setText(String.format(Locale.ENGLISH, "%d", gameState.getScore()));
        scoreView.setTextColor(ContextCompat.getColor(getApplicationContext(), LevelColor.getByValue(gameState.getLevel()).getColor()));
    }

    private String getLivesString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<gameState.getLives(); i++){
            sb.append(getString(R.string.life_indicator));
        }
        return sb.toString();
    }

    private void processGameAction() {
        if (gameController.isGameOver()) {
            endGame();
        } else {
            gameController.nextLevel();
            updateViews();
            resetTimer();
        }
    }

    private void pauseThread(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resetTimer() {
        time = gameState.getTime();
        progressBar.setMax(time);

        if (!timerRunning) {
            timerRunning = true;
            Thread timerThread = new Thread(getTimerRunnable());
            timerThread.start();
        }
    }

    private Runnable getTimerRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                while (timerRunning) {
                    progressBar.setProgress(time);
                    time -= PROGRESS_BAR_UPDATE_DELAY;

                    if (time < 0 && timerRunning) {
                        timerRunning = false;
                        runOnUiThread(getProcessActionRunnable());
                    } else {
                        try {
                            Thread.sleep(PROGRESS_BAR_UPDATE_DELAY);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }

    private Runnable getProcessActionRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                gameController.rightButtonGuessProcessed(-1);
                processGameAction();
                AudioController.play(getApplicationContext(), AudioClip.LIFE_LOST, soundEnabled);
            }
        };
    }

    private void endGame() {
        if(!gameEnded){
            timerRunning = false;
            gameEnded = true;
            AudioController.play(this, AudioClip.GAME_OVER, soundEnabled);
            Intent result = new Intent();
            result.putExtra(getString(R.string.score), gameController.getScore());
            setResult(RESULT_OK, result);
            pauseThread(GAME_OVER_SLEEP_DELAY);
            finish();
        }
    }
}

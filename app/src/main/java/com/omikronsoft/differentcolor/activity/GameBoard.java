package com.omikronsoft.differentcolor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.omikronsoft.differentcolor.R;
import com.omikronsoft.differentcolor.control.GameControl;
import com.omikronsoft.differentcolor.control.GameController;

public class GameBoard extends AppCompatActivity {
    private GameControl gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        prepareHomeButton();

        TextView livesView = (TextView) findViewById(R.id.text_lives);
        TextView scoreView = (TextView) findViewById(R.id.text_score);
        Button[] colorButtons = getColorButtons();

        gameController = getGameController(colorButtons, livesView, scoreView);
        gameController.startNewGame();
    }

    private void prepareHomeButton(){
        ImageButton buttonHome = (ImageButton) findViewById(R.id.button_home);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame();
            }
        });
    }

    private Button[] getColorButtons(){
        Button[] buttons = new Button[4];

        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);

        setOnClickListeners(buttons);

        return buttons;
    }

    private void setOnClickListeners(Button[] buttons){
        for (int i = 0; i<buttons.length; i++) {
            View.OnClickListener listener = getColorButtonListener(i);
            buttons[i].setOnClickListener(listener);
        }
    }

    private GameControl getGameController(Button[] colorButtons, TextView livesView, TextView scoreView){
        return new GameController(colorButtons, livesView, scoreView);
    }

    private View.OnClickListener getColorButtonListener(final int buttonIdx){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameController.processButtonClick(buttonIdx);

                if(gameController.isGameOver()){
                    endGame();
                }
            }
        };
    }

    private void endGame(){
        Intent result = new Intent();
        result.putExtra(getString(R.string.score), gameController.getScore());
        setResult(RESULT_OK, result);
        finish();
    }
}

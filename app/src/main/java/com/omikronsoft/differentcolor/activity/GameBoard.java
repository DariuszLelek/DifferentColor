package com.omikronsoft.differentcolor.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.omikronsoft.differentcolor.R;
import com.omikronsoft.differentcolor.control.GameController;

import java.util.List;

import static android.R.attr.button;

public class GameBoard extends AppCompatActivity {
    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        TextView livesView = (TextView) findViewById(R.id.text_lives);
        TextView scoreView = (TextView) findViewById(R.id.text_score);
        Button[] buttons = getButtons();

        prepareGameController(buttons, livesView, scoreView);
    }

    private Button[] getButtons(){
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
            View.OnClickListener listener = getButtonListener(i);
            buttons[i].setOnClickListener(listener);
        }
    }

    private void prepareGameController(Button[] buttons, TextView livesView, TextView scoreView){
        gameController = new GameController(buttons, livesView, scoreView);
    }

    private View.OnClickListener getButtonListener(final int buttonIdx){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameController.processButtonClick(buttonIdx);
            }
        };
    }
}

package com.omikronsoft.differentcolor.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.omikronsoft.differentcolor.R;

import java.util.Locale;

public class Main extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        prefs = this.getSharedPreferences("DifferentColor", Context.MODE_PRIVATE);
        refreshHighScore();
    }

    public void startNewGame(View view) {
        Intent intent = new Intent(this, GameBoard.class);
        startActivityForResult(intent, 1);
    }

    public void exit(View view) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            int score = (int) extras.get(getString(R.string.score));

            if(score > prefs.getInt(getString(R.string.high_score_key), 0)){
                updateHighScore(score);
                refreshHighScore();
            }

        }
    }

    private void updateHighScore(int score){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(getString(R.string.high_score_key), score);
        edit.apply();
    }

    private void refreshHighScore(){
        TextView highScoreView = (TextView) findViewById(R.id.text_high_score);
        int highScore = prefs.getInt(getString(R.string.high_score_key), 0);
        highScoreView.setText(String.format(Locale.ENGLISH, "%d", highScore));
    }
}

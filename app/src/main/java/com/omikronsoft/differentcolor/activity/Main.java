package com.omikronsoft.differentcolor.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.omikronsoft.differentcolor.R;
import com.omikronsoft.differentcolor.util.PrefsHolder;

import java.util.Locale;

import static com.omikronsoft.differentcolor.R.string.score;

public class Main extends AppCompatActivity {
    private SharedPreferences prefs;
    private ImageButton soundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        prefs = this.getSharedPreferences("DifferentColor", Context.MODE_PRIVATE);
        PrefsHolder.setPrefs(prefs);
        soundButton = (ImageButton) findViewById(R.id.button_sound);

        refreshSoundButtonGraphics();
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

    public void processVoiceButtonClick(View view){
        boolean soundEnabled = prefs.getBoolean(getString(R.string.sound_enabled_key), true);

        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(getString(R.string.sound_enabled_key), !soundEnabled);
        edit.apply();

        refreshSoundButtonGraphics();
    }

    private boolean isSoundEnabled(){
        return prefs.getBoolean(getString(R.string.sound_enabled_key), true);
    }

    private void refreshSoundButtonGraphics(){
        boolean soundEnabled = isSoundEnabled();
        if(soundEnabled){
            soundButton.setImageResource(R.drawable.ic_volume_up_black_100dp);
        }else{
            soundButton.setImageResource(R.drawable.ic_volume_off_black_100dp);
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

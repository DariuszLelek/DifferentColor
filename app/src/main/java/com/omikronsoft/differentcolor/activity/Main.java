package com.omikronsoft.differentcolor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.omikronsoft.differentcolor.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            int score = (int) extras.get(getString(R.string.score));

        }
    }
}

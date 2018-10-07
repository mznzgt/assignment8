package com.example.a8project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class result extends AppCompatActivity {

    private int[] rankResult = new int[]{0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_result);

        //TextView scoreLabel = (TextView)findViewById(R.id.scoreLabel);
       // TextView highScoreLabel = (TextView)findViewById(R.id.highScoreLabel);

        //int score = getIntent().getIntExtra("currentScore",0);
        //scoreLabel.setText(score + "");
        //SharedPreferences setting = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        //currScore = setting.getInt("Current Score", 0);
       /* highScoreLabel.setText(highScore);
        if(score > highScore){
            highScoreLabel.setText("High Score : " + score);
            SharedPreferences.Editor editor = setting.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.apply();
            //highest = score;
        }
        else{
            highScoreLabel.setText("High Score is :" + highScore);
        }*/
        //for(int i = 0;i<rankResult.length-1;i++){
           // if (score > rankResult[i]){
           //     rankResult[i+1] = rankResult[i];
                //rankResult[i] = score;
           // }
        //}
    }

    public int returnFirst(){
        return rankResult[0];
    }

    public int returnSecond(){
        return rankResult[1];
    }

    public int returnThird(){
        return rankResult[2];
    }

    public int returnFourth(){
        return rankResult[3];
    }

    public int returnFifth(){
        return rankResult[4];
    }

/*
    public void playAgain(View view){
        startActivity(new Intent(getApplicationContext(),gameView.class));
    }

    public void returnToMain(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }*/

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}

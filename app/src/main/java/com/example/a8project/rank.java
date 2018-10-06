package com.example.a8project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class rank extends AppCompatActivity {
    result r = new result();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView first = (TextView)findViewById(R.id.first);
        TextView second = (TextView)findViewById(R.id.second);
        TextView third = (TextView)findViewById(R.id.third);
        TextView fourth = (TextView)findViewById(R.id.fourth);
        TextView fifth = (TextView)findViewById(R.id.fifth);

        first.setText(String.valueOf(r.returnFirst()));
        second.setText(String.valueOf(r.returnSecond()));
        third.setText(String.valueOf(r.returnThird()));
        fourth.setText(String.valueOf(r.returnFourth()));
        fifth.setText(String.valueOf(r.returnFifth()));

        //int score = getIntent().getIntExtra("SCORE",0);
        //second.setText(score + "");
        SharedPreferences setting = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        int currentScore = setting.getInt("currentScore", 0);
        int firstScore = setting.getInt("firstScore", 0);
        int secondScore = setting.getInt("secondScore", 0);
        int thirdScore = setting.getInt("thirdScore", 0);
        int fourthScore = setting.getInt("fourthScore", 0);
        int fifthScore = setting.getInt("fifthScore", 0);
        System.out.println(firstScore +"," + secondScore + "," + currentScore);
        //third.setText(currentScore +"");
        //editor.putInt("firstScore",currentScore);
        //editor.apply();
        first.setText(firstScore+"");
        second.setText(secondScore+"");
        third.setText(thirdScore+"");
        fourth.setText(fourthScore+"");
        fifth.setText(fifthScore+"");


        if(currentScore > firstScore){
            second.setText(firstScore+"");
            editor.putInt("secondScore",firstScore);
            editor.commit();

            third.setText(secondScore+"");
            editor.putInt("thirdScore",secondScore);
            editor.commit();

            fourth.setText(thirdScore+"");
            editor.putInt("fourthScore",thirdScore);
            editor.commit();

            fifth.setText(fourthScore+"");
            editor.putInt("fifthScore",fourthScore);
            editor.commit();

            firstScore = currentScore;
            first.setText(firstScore+"");
            editor.putInt("firstScore",firstScore);
            editor.commit();
        }

        else if(currentScore > secondScore){
            first.setText(firstScore+"");
            editor.putInt("firstScore",firstScore);
            editor.commit();

            third.setText(secondScore+"");
            editor.putInt("thirdScore",secondScore);
            editor.commit();

            fourth.setText(thirdScore+"");
            editor.putInt("fourthScore",thirdScore);
            editor.commit();

            fifth.setText(fourthScore+"");
            editor.putInt("fifthScore",fourthScore);
            editor.commit();

            secondScore = currentScore;
            second.setText(secondScore+"");
            editor.putInt("secondScore",secondScore);
            editor.commit();
        }

        else if(currentScore > thirdScore){
            fourth.setText(thirdScore+"");
            editor.putInt("fourthScore",thirdScore);
            editor.commit();

            fifth.setText(fourthScore+"");
            editor.putInt("fifthScore",fourthScore);
            editor.commit();

            thirdScore = currentScore;
            third.setText(thirdScore+"");
            editor.putInt("thirdScore",thirdScore);
            editor.commit();
        }

        else if(currentScore > fourthScore) {
            fifth.setText(fourthScore+"");
            editor.putInt("fifthScore",fourthScore);
            editor.commit();

            fourthScore = currentScore;
            fourth.setText(fourthScore+"");
            editor.putInt("fourthScore", fourthScore);
            editor.commit();
        }

        else if(currentScore > fifthScore){
            fifthScore = currentScore;
            fifth.setText(fifthScore+"");
            editor.putInt("fifthScore",fifthScore);
            editor.commit();
        }

        /*if(score > highScore){
            first.setText(score + "");

            SharedPreferences.Editor editor = setting.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }
        else{
            fifth.setText("" + highScore);
        }*/
    }

    public void playAgain(View view){
        startActivity(new Intent(getApplicationContext(),gameView.class));
    }

    public void returnToMain(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}

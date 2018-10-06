package com.example.a8project;

import android.content.Intent;
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
    }

    public void playAgain(View view){
        startActivity(new Intent(getApplicationContext(),gameView.class));
    }

    public void returnToMain(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}

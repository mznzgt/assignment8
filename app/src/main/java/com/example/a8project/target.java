package com.example.a8project;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

public class target extends Canvas {

    private Bitmap bitmap;
    private float length;
    private float height;

    private float x;
    private float y;

    private float speedX,speedY;
    Random random = new Random();
    int randomSpeedX = 5;
    int randomSpeedY = 5;

    public target(Context context){
        length = 100;
        height = 100;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.unnamed);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (length), (int) (height), false);
        speedX = random.nextInt(randomSpeedX) + 1;
        speedY = random.nextInt(randomSpeedY) + 1;
        //x = random.nextInt(850) + 30;
        //y = random.nextInt(1500) + 50;
        x = Resources.getSystem().getDisplayMetrics().widthPixels/2 - 15;
        y = 50;

    }

    public float getSpeedX(){
        return speedX;
    }

    public float getSpeedY(){
        return speedY;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getLength(){
        return length;
    }
}

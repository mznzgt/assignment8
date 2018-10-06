package com.example.a8project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

public class bullet extends Ball {
    RectF rect;
    private Bitmap bitmap;
    private float length;
    private float height;

    private float x;
    private float y;

    private float speedX,speedY;
    private int numBullets;
    Random random = new Random();
    int randomSpeedX = 3;
    int randomSpeedY = 3;

    public bullet(Context context){
        rect = new RectF();
        length = 40;
        height = 40;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (length), (int) (height), false);
        speedX = random.nextInt(randomSpeedX) + 1;
        speedY = random.nextInt(randomSpeedY) + 1;
        numBullets = 30;
        x = random.nextInt(900) +151;
        y = random.nextInt(1600) + 152;
    }

    public float getSpeedX(){
        return speedX;
    }

    public float getSpeedY(){
        return speedY;
    }

    public int getNumBullets(){
        return numBullets;
    }

    public RectF getRect(){
        return rect;
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

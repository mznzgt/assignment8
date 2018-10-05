package com.example.a8project;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class playerBall extends Canvas {
    RectF rect;
    private Bitmap bitmap;
    private float length;
    private float height;

    private float x;

    private float y;


    public playerBall(Context context){

        rect = new RectF();
        length = 100;
        height = 100;

        x = Resources.getSystem().getDisplayMetrics().widthPixels/2 - 15;
        y = Resources.getSystem().getDisplayMetrics().heightPixels;
        //x=600;
        //y=600;


        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);

        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);
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

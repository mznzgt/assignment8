
/*
package com.example.a8project;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Random;

public class game extends AppCompatActivity {

    SensorManager sensorMgr;
    Sensor accelerometer;
    AccelerometerListener listener;
    private float gravity;
    private float Xposition;
    private float Yposition;

    // Touch event
    GestureDetectorCompat gestureDetector;
    ScaleGestureDetector scaleGestureDetector;
    MyGestureListener gestureListener;
    ScaleGestureDetector scaleDetector;
    MyScaleListener scaleListener;
    float circleSize = 100;

    class MyGestureListener implements GestureDetector.OnGestureListener{
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            Log.d("202","Down");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
            Log.d("202", "ShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.d("202", "Single tap up");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float x, float y) {
            Log.d("202","Scroll" + x + "," + y  );
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            Log.d("202", "LongPress");
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d("202","Fling");
            return false;
        }
    }

    class MyScaleListener implements  ScaleGestureDetector.OnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if(circleSize < 10){
                circleSize = 10;
            }
            if(circleSize > 300){
                circleSize = 300;
            }
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

        }
    }

    class AccelerometerListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent sensorEvent){
            Log.d("202","" + sensorEvent.values[0] + "," + sensorEvent.values[1]
                    + "," + sensorEvent.values[2]);
            gravity = sensorEvent.values[2];
            Xposition = sensorEvent.values[0];
            Yposition = sensorEvent.values[1];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i){

        }
    }

    public class GraphicsView extends View {
        private float x,y;
        final int MaxTouch = 1;
        float xTouch, yTouch;
        int bulletX;
        int bulletY;
        Random random = new Random();
        int speedX;
        int speedY;

        public GraphicsView(Context c){
            super(c);
            x = 0; y =0;
            xTouch = 0;
            yTouch = 0;
             bulletX = random.nextInt(1080);
             bulletY = random.nextInt(2128);
             speedX = random.nextInt(5) + 1;
            speedY = random.nextInt(5) + 1;
        }

        @Override
        protected  void onDraw(Canvas canvas){

            super.onDraw(canvas);
            Paint ball = new Paint();
            Resources res = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.ball);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap,100,100,false);

            canvas.drawBitmap(resizedBitmap,x,y,ball);

            Paint bullet = new Paint();
            bullet.setColor(Color.YELLOW);


                canvas.drawCircle(bulletX, bulletY, 20, bullet);

            if(bulletX < 20 || bulletX > canvas.getWidth() -20){
                speedX = -speedX;
            }

            if(bulletY < 20 || bulletY > canvas.getHeight()-20){
                speedY = -speedY;
            }

            bulletX += speedX;
            bulletY +=speedY;


                int height = canvas.getHeight() -100;
                int width = canvas.getWidth() -100;
                float aX = Xposition;

                if(aX < 0){
                    aX = Math.abs(aX);

                }


                else if(aX >0){
                    aX = aX - 2*aX;

                }


                x+=aX * 2;

                y+=Yposition *2;

                if(x > width){
                    x = width;
                }

                if(x <= 0){
                    x = 0;
                }


                if(y > height){
                    y = height;
                }

                if(y <= 0){
                    y = 0;
                }

            invalidate();
        }


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            scaleDetector.onTouchEvent(event);
            xTouch = event.getX();
            yTouch = event.getY();

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
            }
            invalidate();
            return true;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("202","OnCreate");
        RelativeLayout layout = new RelativeLayout(this);
            GraphicsView gv = new GraphicsView(this);
            layout.addView(gv);
            gv.setBackgroundResource(R.drawable.game_background);
            setContentView(layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        PackageManager pmgr = getPackageManager();
        for (FeatureInfo fi : pmgr.getSystemAvailableFeatures()){
            Log.d("202",fi.toString());
        }

        if(pmgr.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)){
            Log.d("202","Have accelerometer");
        }

        //setContentView(R.layout.activity_main);
        sensorMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.d("202", "Obtained accelerometer" + accelerometer);
        listener = new AccelerometerListener();

        sensorMgr.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        // Touch event
        gestureListener = new MyGestureListener();
        gestureDetector = new GestureDetectorCompat(this, gestureListener);
        scaleListener = new MyScaleListener();
        scaleDetector = new ScaleGestureDetector(this, scaleListener);
        Log.d("COMPX202", "Finishing onCreate");
    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(listener,accelerometer);
        //Log.d("202", "OnPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("202", "OnStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("202", "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("202", "OnResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("202", "OnDestroy");
    }
}


*/

package com.example.a8project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.RandomAccess;

public class gameView extends AppCompatActivity {
    RectF playerBallRect;
    RectF [] bulletsRect = new RectF[1000];
    RectF targetRect;
    private Paint paint;
    private playerBall playBall;
    private bullet bulletBall;
    private target target;
    bullet[] bullets = new bullet[1000];
    float [] bulletsX = new float[1000];
    float [] bulletsY = new float[1000];
    float []bulletSpeedX = new float[1000];
    float []bulletSpeedY = new float[1000];
    private boolean gameOver = false;
    private boolean gamePlaying = false;
    private int score = 0;
    private static int level = 1;
    private int numBullets;
    private static int controlNumBullets = 1;
    private boolean dragAreaHelper = false;
    private boolean ySpeedControl = true;



    // gravity
    SensorManager sensorMgr;
    Sensor accelerometer;
    AccelerometerListener listener;
    private float gravity;
    private float Xposition;
    private float Yposition;
    private float firstTouchY;
    private float secondTouchY;

    //Gesture
    GestureDetectorCompat gestureDetector;
    MyGestureListener gestureListener;
    ScaleGestureDetector scaleDetector;
    MyScaleListener scaleListener;


    class AccelerometerListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            gravity = sensorEvent.values[2];
            Xposition = sensorEvent.values[0];
            //Yposition = sensorEvent.values[1];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    public class gameViewControl extends View {
        Context context;
        float playerBallX;
        float playerBallY;
        float targetXSpeed, targetYSpeed;
        float targetX, targetY;

        public gameViewControl(Context context) {

            super(context);

            this.context = context;
            Random random = new Random();
            start();
            paint = new Paint();
            playerBallX = playBall.getX();
            playerBallY = playBall.getY();

        }


        private void start() {

            playBall = new playerBall(context);


            //controlNumBullets = controlNumBullets + level;
            System.out.println("number of bullet:" + controlNumBullets +"  " + "current level : " + level );

            for (int i = 0; i< 1000; i++ ){
                bullets[i] = new bullet(context);
                bulletsX[i] = bullets[i].getX();
                bulletsY[i] = bullets[i].getY();
            }

            for(int i = 0; i< 1000; i++){
                bulletSpeedX[i] = bullets[i].getSpeedX();
                bulletSpeedY[i] = bullets[i].getSpeedY();
            }

            target = new target(context);

            //targetXSpeed = target.getSpeedX();
            //targetYSpeed = target.getSpeedY();
            targetX = target.getX();
            targetY = target.getY();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.argb(255, 255, 255, 255));
            Paint paint2 = new Paint();
            paint2.setColor(Color.WHITE);
            paint2.setAlpha(0);
            Paint yellow = new Paint();
            yellow.setColor(Color.YELLOW);

 /////////////////////////////////////////////   player ball movement

            canvas.drawBitmap(playBall.getBitmap(), playerBallX, playerBallY, paint);
            playerBallRect = new RectF(playerBallX,playerBallY,playerBallX + 100,playerBallY + 100);
            canvas.drawRect(playerBallRect,paint2);
            //                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   canvas.drawRect(playerBallX,playerBallY,playerBallX + 100,playerBallY + 100,paint2);
            int height = canvas.getHeight() -100;
            int width = canvas.getWidth() -100;
            float aX = Xposition;
            if(aX < 0){
                aX = Math.abs(aX);

            }
            else if(aX >0){
                aX = aX - 2*aX;
            }
            playerBallX+=aX * 2;
            playerBallY+=Yposition *2;
            if(playerBallX > width){
                playerBallX = width;
            }
            if(playerBallX <= 0){
                playerBallX = 0;
            }
            if(playerBallY > height){
                playerBallY = height;
            }
            if(playerBallY <= 60){
                playerBallY = 60;
            }
            //////////////////////////////////// end playerball's movement

            //////////////////////////////////////draw bullets
            //canvas.drawLine(0,250,0,canvas.getWidth(),yellow);
            int a;
            a = controlNumBullets;
            for(int i = 0; i < a; i++) {
                canvas.drawBitmap(bullets[i].getBitmap(), bulletsX[i], bulletsY[i], paint);
                bulletsRect[i] = new RectF(bulletsX[i], bulletsY[i],bulletsX[i]+40, bulletsY[i]+40);
                canvas.drawRect(bulletsRect[i],paint2);
                if(bulletsX[i] < 30 || bulletsX[i] > canvas.getWidth() -30){
                    bulletSpeedX[i] = -bulletSpeedX[i];
                }

                if(bulletsY[i] < 100 || bulletsY[i] > canvas.getHeight()-350){
                    bulletSpeedY[i] = -bulletSpeedY[i];
                }

                bulletsX[i] += bulletSpeedX[i];
                bulletsY[i] += bulletSpeedY[i];

            }

            /////////////////////////////////////// bullets draw end
            /////////////////////////////////////// draw target
            canvas.drawBitmap(target.getBitmap(), targetX,targetY,paint);
            targetRect = new RectF(targetX,targetY,targetX+100,targetY+100);
            canvas.drawRect(targetRect,paint2);
            //System.out.println(canvas.getHeight()); 2128
/*


            if(targetX < 30 || targetX > canvas.getWidth() -100){
                targetXSpeed = -targetXSpeed;
            }

            if(targetY < 50 || targetY > canvas.getHeight()-250){
                targetYSpeed = -targetYSpeed;
            }

            targetX += targetXSpeed;
            targetY += targetYSpeed;*/

            /////////////////////////////  collision detection

            for(int i = 0; i< controlNumBullets; i++){
                if(RectF.intersects(playerBallRect,bulletsRect[i])){
                    gameOver = true;
                }
            }

            if(RectF.intersects(playerBallRect,targetRect)){
                score = score + 10;
                //Random random = new Random();
                //targetX = random.nextInt(800) + 50;
                //targetY = random.nextInt(1500) + 50;
                //targetXSpeed = random.nextInt(5) + 1;
                //targetYSpeed = random.nextInt(5) + 1;
                playerBallX = playBall.getX();
                playerBallY = playBall.getY();
                level = level + 1;
                Yposition = 0;
                controlNumBullets = level;
                ySpeedControl = true;
            }

            //////////////////////////////////////// end collision detection

            /*for(int i = 0; i< numBullets; i++) {
                if (RectF.intersects(targetRect, bulletsRect[i])){

                }
            }*/

            if(gameOver == true){
                //gameOver();
            }

            Paint dragHelperGreen = new Paint();
            dragHelperGreen.setColor(Color.GREEN);
            Paint dragHelperRed = new Paint();
            dragHelperRed.setColor(Color.RED);

            if(dragAreaHelper == false){
                canvas.drawLine(0,1828,canvas.getWidth(),1828,dragHelperRed);
            }
            else if(dragAreaHelper == true){
                canvas.drawLine(0,1828,canvas.getWidth(),1828,dragHelperGreen);
            }


            paint.setColor(Color.argb(255, 249, 129, 0));
            paint.setTextSize(40);
            canvas.drawText("Score: " + score, 10, 50, paint);
            canvas.drawText("Level: " + level, canvas.getWidth()-250, 50,paint);
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            gestureDetector.onTouchEvent(event);
            scaleDetector.onTouchEvent(event);

            float a =0,b=0,c=0,d=0;
            dragAreaHelper = false;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("COMPX202", "Down");
                        a = event.getY(0);
                        if(a > 1828 & a < 2127) {
                            firstTouchY = a;
                            dragAreaHelper = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("COMPX202", "Up");
                        b = event.getY(0);
                        //d = event.getX(0);
                        if(b > 1828 & b < 2127) {
                            //dragAreaHelper = true;
                            secondTouchY = b;
                        }
                        if(ySpeedControl == true) {
                            if (secondTouchY > 1828 & secondTouchY < 2127 & firstTouchY > 1828 & firstTouchY < 2127) {
                                c = firstTouchY - secondTouchY;
                                if (c > 0) {
                                    c = c / 30;
                                    Yposition = -c;
                                    System.out.println(firstTouchY + ",  " + secondTouchY + ",  " + Yposition);
                                }
                            }
                        }
                        ySpeedControl = false;
                        firstTouchY = 0;
                        secondTouchY = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //Log.d("COMPX202", "Move");
                        d = event.getY(0);
                        if(d > 1828 & d < 2127){
                            dragAreaHelper = true;
                        }
                        break;
                }
            invalidate();
            return true;
        }
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d("202", "OnCreate");
            RelativeLayout layout = new RelativeLayout(this);
            gameViewControl gvc = new gameViewControl(this);
            layout.addView(gvc);
            gvc.setBackgroundResource(R.drawable.game_background);
            setContentView(layout);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            //setContentView(R.layout.activity_main);
        gestureListener = new MyGestureListener();
        gestureDetector = new GestureDetectorCompat(this, gestureListener);
        scaleListener = new MyScaleListener();
        scaleDetector = new ScaleGestureDetector(this, scaleListener);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new AccelerometerListener();
        sensorMgr.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        Log.d("COMPX202", "Finishing onCreate");

        }

        public void gameOver(){
            this.onStop();
            this.finish();
            Intent intent2 = new Intent(getApplicationContext(),result.class);
            intent2.putExtra("SCORE", score);
            startActivity(intent2);
            startActivity(new Intent(getApplicationContext(),result.class));
        }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class MyGestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("COMPX202", "Down");
            return false;
        }
        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("COMPX202", "Show press");
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("COMPX202", "Single tap up");
            return false;
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("COMPX202", "Scroll " + distanceX + ", " + distanceY);
            return false;
        }
        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("COMPX202", "Long press");
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("COMPX202", "Fling");
            return false;
        }
    }

    class MyScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("COMPX202", "Scale by " + detector.getScaleFactor());
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

}

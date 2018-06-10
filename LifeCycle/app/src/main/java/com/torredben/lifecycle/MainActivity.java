package com.torredben.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity {

    public static long UPDATE_ERVERY=200;
    private static String TAG = "class:MainActivity";

    protected TextView  counterView;
    protected Button  startButton;
    protected Button  stopButton;
    protected Boolean timeRunning;
    protected long startedAt;
    protected long lastStoped;

    protected Handler myHandler;
    protected UpdateTimer updateTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterView = findViewById(R.id.timer);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        timeRunning=false;
        Log.d(TAG, "onCreate: +++++++++++++");
    }

    @Override
    protected void onStart() {
        super.onStart();
        restoreInstance();

        if (timeRunning) {

            startedAt =System.currentTimeMillis();

            enableButton();

            myHandler = new Handler();
            updateTimer = new UpdateTimer();
            updateTimer.setActivity(this);
            updateTimer.setHandler(myHandler);
            myHandler.postDelayed(updateTimer, UPDATE_ERVERY);

            Log.d(TAG, "onStart: +++++++++++++");
        } else {
            startedAt = 0;
            lastStoped = 0;
        }

        setTimeDisplay();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveInstance();

        Log.d(TAG, "onStop: +++++++++++++++");
        if(timeRunning){
            myHandler.removeCallbacks(updateTimer);
            updateTimer = null;
            myHandler = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ++++++++++++++");
    }

    public void clickStart(View view) {
        timeRunning = true;
        startedAt =System.currentTimeMillis();
        enableButton();

        myHandler = new Handler();
        updateTimer = new UpdateTimer();
        updateTimer.setActivity(this);
        updateTimer.setHandler(myHandler);


        //Log.d(TAG, "clickStart: myHandler.postDelayed-------");
        myHandler.postDelayed(updateTimer, UPDATE_ERVERY);
        //Log.d(TAG, "clickStart: myHandler.postDelayed+++++++");
        saveInstance();
    }



    public void clickStop(View view) {
        timeRunning =false;
        lastStoped = System.currentTimeMillis();
        enableButton();

        myHandler.removeCallbacks(updateTimer);
        myHandler = null;
        updateTimer.removeUpdateTimer();
        updateTimer = null;
    }

    protected void enableButton(){
        startButton.setEnabled(!timeRunning);
        stopButton.setEnabled(timeRunning);
    }

    protected  void setTimeDisplay(){
        String displayText;
        long timeNow;
        long diffTime;
        long secendsCounter;
        long minutesCounter;
        long hoursCounter;

        if(timeRunning){
            timeNow = System.currentTimeMillis();
        }else {
            timeNow = lastStoped;
        }

        diffTime = timeNow - startedAt;

        if(diffTime<0) {
            diffTime = 0;
        }

        secendsCounter = diffTime / 1000;
        minutesCounter = secendsCounter / 60;
        hoursCounter = minutesCounter / 60;

        secendsCounter =secendsCounter % 60;
        minutesCounter = minutesCounter % 60;

        displayText = String.format("%d",hoursCounter) + ":" +
                String.format("%02d",minutesCounter) + ":" +
                String.format("%02d", secendsCounter);

        counterView.setText(displayText);
    }

    protected void saveInstance() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("SAVETIMERSTATE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("timeRunning",timeRunning);
        editor.commit();
    }

    protected void restoreInstance(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("SAVETIMERSTATE",Context.MODE_PRIVATE);
        timeRunning = sharedPreferences.getBoolean("timeRunning",false);
    }
}

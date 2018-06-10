package com.torredben.lifecycle;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

public class UpdateTimer implements Runnable {

    private Handler mHandler;
    private MainActivity mActivity;

    private static String TAG = "class:UpdateTimer";

    @Override
    public void run() {
        if(mHandler != null ){

            //Log.d(TAG, "run: myHandler.postDelayed-------");
            mHandler.postDelayed(this,MainActivity.UPDATE_ERVERY);
            //Log.d(TAG, "run: myHandler.postDelayed+++++++");

            mActivity.setTimeDisplay();
        }
    }

    public void setHandler(Handler handler){
        mHandler = handler;
    }

    public void setActivity(MainActivity activity){
        mActivity = activity;
    }

    public void removeUpdateTimer(){
        mHandler = null;
    }
}

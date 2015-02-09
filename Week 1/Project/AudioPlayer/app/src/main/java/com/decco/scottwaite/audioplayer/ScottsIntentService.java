package com.decco.scottwaite.audioplayer;
/*
Created By: Scott Waite
Course: Java II
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: 02/08/2015
 */
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ScottsIntentService extends IntentService {

    private static final String TAG = "com.decco.scottwaite.audioplayer";

    public ScottsIntentService() {
        super("ScottsIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        //This is what the service does
        Log.i(TAG, "The service has now started");

    }
}

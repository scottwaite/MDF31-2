package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Mobile Development Frameworks III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: December 2, 2014
*/

import android.app.Activity;
import android.os.Bundle;


public class MyPrefsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPrefsFragment())
                .commit();
    }
}

/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Mapping Photos
Date: May 28, 2015
*/

package com.scottwaite.mappingphotos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}

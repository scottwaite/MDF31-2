package com.decco.scottwaite.audioplayer;
/*
Created By: Scott Waite
Course: Java II
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: 02/08/2015
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ThirdSectionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_section_fragment, container, false);
        return view;
    }


}

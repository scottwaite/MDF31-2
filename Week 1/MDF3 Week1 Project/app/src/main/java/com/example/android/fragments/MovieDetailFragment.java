package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Mobile Development Frameworks III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: December 2, 2014
*/

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottwaite.android.fragments.data.Movie;

import java.text.NumberFormat;

public class MovieDetailFragment extends Fragment {

    Movie movie;

    public MovieDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if (b != null && b.containsKey(Movie.MOVIE_NAME)) {
            movie = new Movie(b);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        if (movie != null) {

            TextView tvName = (TextView) view.findViewById(R.id.tvMovieName);
            tvName.setText(movie.getMovieName());

            TextView tvInstructions = (TextView) view.findViewById(R.id.tvInstructions);
            tvInstructions.setText(movie.getInstructions());

            ImageView ivPicture = (ImageView) view.findViewById(R.id.ivMovieImage);
            ivPicture.setImageResource(movie.getImageResource());

        }

        return view;
    }

}

package com.scottwaite.android.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scottwaite.android.fragments.data.Movie;
import com.scottwaite.android.fragments.data.MovieData;

import java.util.List;

/*
Created By: Scott Waite
Course: Mobile Development Frameworks III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: December 2, 2014
*/

public class MovieListFragment extends ListFragment {

    List<Movie> movies = new MovieData().getMovies();
    private Callbacks activity;

    public MovieListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieArrayAdapter adapter = new MovieArrayAdapter(getActivity(),
                R.layout.movie_listitem,
                movies);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_list_fragment, container, false);
        return rootView;
    }

    public interface Callbacks {
        public void onItemSelected(Movie movie);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Movie movie = movies.get(position);
        activity.onItemSelected(movie);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (Callbacks) activity;
    }
}


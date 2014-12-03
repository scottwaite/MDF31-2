package com.scottwaite.android.fragments.data;

/*
Created By: Scott Waite
Course: Mobile Development Frameworks III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: December 2, 2014
*/

import com.scottwaite.android.fragments.R;

import java.util.ArrayList;
import java.util.List;

public class MovieData {

	private List<Movie> movies = new ArrayList<Movie>();
	public List<Movie> getMovies() {
		return movies;
	}

	public MovieData() {
		movies.add(new Movie("Meow Mix", R.drawable.meowmix, 10.95,
				"Lyric text"));
		movies.add(new Movie("Ice Cream Truck", R.drawable.icecream, 10.95,
				"Lyric text"));
		movies.add(new Movie("Bird Song", R.drawable.birdsong, 10.95,
				"Lyric text"));
	}


}

package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Mobile Development Frameworks III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: December 2, 2014
*/

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottwaite.android.fragments.data.Movie;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

	private Context context;
	private List<Movie> objects;
	
	public MovieArrayAdapter(Context context, int resource, List<Movie> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Movie movie = objects.get(position);
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.movie_listitem, null);
		
		ImageView image = (ImageView) view.findViewById(R.id.ivMovieImage);
		image.setImageResource(movie.getImageResource());
		
		TextView tv = (TextView) view.findViewById(R.id.tvMovieName);
		tv.setText(movie.getMovieName());
		
		return view;
	}

}

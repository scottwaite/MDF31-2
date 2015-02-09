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
import android.view.MenuItem;

public class MovieDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);

		getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            MovieDetailFragment fragment = new MovieDetailFragment();

            Bundle b = getIntent().getBundleExtra(MainActivity.MOVIE_BUNDLE);
            fragment.setArguments(b);

            getFragmentManager().beginTransaction()
                    .add(R.id.detailContainer, fragment)
                    .commit();
        }

    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
            finish();
        }
		return true;
	}

}

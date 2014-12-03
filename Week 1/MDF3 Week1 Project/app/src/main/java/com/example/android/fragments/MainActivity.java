package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Mobile Development Frameworks III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: December 2, 2014
*/

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.scottwaite.android.fragments.data.Movie;


public class MainActivity extends Activity
    implements MovieListFragment.Callbacks {

    public static final String MOVIE_BUNDLE = "MOVIE_BUNDLE";
    private static final int REQUEST_CODE = 1001;

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detailContainer) != null) {
            isTwoPane = true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent intent = new Intent();
            intent.setClass(this, MyPrefsActivity.class);
            startActivityForResult(intent, 1002);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Movie movie) {
        Bundle b = movie.toBundle();

        if (isTwoPane) {
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(b);
            getFragmentManager().beginTransaction()
                    .replace(R.id.detailContainer, fragment)
                    .commit();
        }
        else {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(MOVIE_BUNDLE, b);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1002) {
            SharedPreferences myPrefs =
                    PreferenceManager.getDefaultSharedPreferences(this);
            boolean api_data = myPrefs.getBoolean("api_data", false);
            Toast.makeText(this, "Play in background: " + api_data, Toast.LENGTH_SHORT).show();
        }
    }
}

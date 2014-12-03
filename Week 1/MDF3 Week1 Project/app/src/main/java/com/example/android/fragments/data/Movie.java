package com.scottwaite.android.fragments.data;

/*
Created By: Scott Waite
Course: Mobile Development Frameworks III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: December 2, 2014
*/

import android.os.Bundle;

public class Movie {

    public static final String MOVIE_NAME = "movieName";
    public static final String IMAGE_RESOURCE = "imageResource";
    public static final String PRICE = "price";
    public static final String INSTRUCTIONS = "instructions";

    private String movieName;
    private int imageResource;
    private String instructions;
    private double price;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //	Used when creating the data object
    public Movie(String id, int imageResource, double price, String instructions) {
        this.movieName = id;
        this.imageResource = imageResource;
        this.price = price;
        this.instructions = instructions;
    }

    public Movie(Bundle b) {
        if (b != null) {
            this.movieName = b.getString(MOVIE_NAME);
            this.imageResource = b.getInt(IMAGE_RESOURCE);
            this.price = b.getDouble(PRICE);
            this.instructions = b.getString(INSTRUCTIONS);
        }
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(MOVIE_NAME, this.movieName);
        b.putInt(IMAGE_RESOURCE, this.imageResource);
        b.putDouble(PRICE, this.price);
        b.putString(INSTRUCTIONS, this.instructions);
        return b;
    }

    @Override
    public String toString() {
        return movieName;
    }

}


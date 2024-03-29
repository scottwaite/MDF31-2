/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Fundamentals Part 2
Date: 05/17/2015
*/

package com.scottwaite.audioservice;

public class AudioClass {

    private long id;
    private String name;
    private String artist;

    public AudioClass(long id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    public long getId(){
      return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getArtist(){
        return this.artist;
    }

}

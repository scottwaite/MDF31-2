/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Service Fundamentals
Date: 05/08/2015
*/




package com.scottwaite.audioservice;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class MediaService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer; // the media player
    private Integer songPosition; // position of song being played
    private ArrayList<Integer> audioResId = new ArrayList<Integer>();
    private boolean isPaused = false;
    private boolean shouldLoop = false;
    private boolean shouldShuffle = false;
    private final IBinder audioBinder = new AudioBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return audioBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
    }

    public void onCreate() {
        super.onCreate(); // call super class method
        songPosition = 0; // position in song array of first track
        mediaPlayer = new MediaPlayer(); // create the media player

        // get all audio files resource ids from the res/raw folder
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                int resId = fields[i].getInt(fields[i]);
                audioResId.add(resId);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // set up media player properties
        mediaPlayer.setAudioSessionId(AudioManager.STREAM_MUSIC);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
    }

    public void playSong() {
       if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPaused = true;
        }
        else {
           if (!isPaused) {
               if (shouldShuffle) {
                   songPosition = new Random().nextInt(audioResId.size());
                   Log.i("MediaService", "Random Song Position: " + songPosition);
               }

               mediaPlayer.reset();
               mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResId.get(songPosition));
           }


            mediaPlayer.start();
            initNotification();
       }

    }

    public void setLoop(boolean shouldLoop) {
    this.shouldLoop = shouldLoop;

    }




    public void setShuffle(boolean shouldShuffle){
        this.shouldShuffle = shouldShuffle;
    }




    public void loopSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPaused = true;
        }
        else {
            if (!isPaused) {
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResId.get(songPosition));
            }

            mediaPlayer.start();


        }

    }




    public void stopSong() {
        mediaPlayer.stop();
        isPaused = false;
    }

    public void nextSong() {
        mediaPlayer.reset();
        if (shouldShuffle && !shouldLoop) {
            songPosition = new Random().nextInt(audioResId.size());
            Log.i("MediaService", "Random Song Position: " + songPosition);
        } else if (!shouldShuffle && !shouldLoop) {

            if (songPosition < audioResId.size()-1) {
                songPosition++;
            } else {
                songPosition = 0;
            }

        }

        Log.i("MediaService", "song pos: " + songPosition + " -- array size: " + audioResId.size());


        mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResId.get(songPosition));
        mediaPlayer.start();
    }

    public void previousSong() {
        mediaPlayer.reset();
        if (shouldShuffle && !shouldLoop) {
            songPosition = new Random().nextInt(audioResId.size());
            Log.i("MediaService", "Random Song Position: " + songPosition);
        } else if (!shouldShuffle && ! shouldLoop) {

            if (songPosition > 0) {
                songPosition--;
            } else {
                songPosition = audioResId.size()-1;
            }
        }
        Log.i("MediaService", "song pos: " + songPosition + " -- array size: " + audioResId.size());


        mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResId.get(songPosition));
        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (shouldShuffle && !shouldLoop) {
            songPosition = new Random().nextInt(audioResId.size());
            Log.i("MediaService", "Random Song Position: " + songPosition);
        } else if (!shouldShuffle && ! shouldLoop) {
            if (songPosition < audioResId.size()) {
                songPosition++;
            } else {
                songPosition = 0;
            }
        }


        mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResId.get(songPosition));
        mediaPlayer.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    public class AudioBinder extends Binder {
        MediaService getService() {
            return MediaService.this;
        }
    }







    // Setup the ID for the notification bar
    private static final int NOTIFICATION_ID = 1;



    // Create a notification so the app can be accessed from the notification bar
    private void initNotification() {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "Audio Service";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        Context context = getApplicationContext();
        CharSequence contentTitle = "Audio Service";
        CharSequence contentText = "Return to Player";
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText,
                contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    // Clear notification
    private void cancelNotification() {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}

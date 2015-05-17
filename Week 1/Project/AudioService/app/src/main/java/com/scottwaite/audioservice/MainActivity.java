/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Fundamentals Part 2
Date: 05/17/2015
*/

package com.scottwaite.audioservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
    */
    public class PlaceholderFragment extends Fragment {

        private static final String TAG="mainactivity";
        private MediaService mediaService;
        private Intent playIntent;
        private boolean isBound = false;

        View rv;
        ViewGroup container;
        LayoutInflater inflater;
        private ServiceConnection serviceConn;

        private CheckBox cbShuffle;
        private CheckBox cbLoop;

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            this.container = container;
            this.inflater = inflater;
            this.rv = rootView;

            Button btnPrevious = (Button)rootView.findViewById(R.id.btnPrevious);
            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HandlePreviousButtonClick();
                }
            });

            Button btnPlay = (Button)rootView.findViewById(R.id.btnPlay);
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HandlePlayButtonClick();
                }
            });

            Button btnStop = (Button)rootView.findViewById(R.id.btnStop);
            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HandleStopButtonClick();
                }
            });

            Button btnNext = (Button)rootView.findViewById(R.id.btnNext);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HandleNextButtonClick();
                }
            });

            cbLoop = (CheckBox)rootView.findViewById(R.id.cbLoop);
            cbLoop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleLoop();
                }
            });

            cbShuffle = (CheckBox)rootView.findViewById(R.id.cbShuffle);
            cbShuffle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleShuffle();
                }
            });

            return rootView;
        }
/*
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);

            container.removeAllViews();
            rv = inflater.inflate(R.layout.fragment_main, container, true);
        }
*/
        // create the connection to the service to play music from
        private ServiceConnection serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MediaService.AudioBinder binder = (MediaService.AudioBinder) service;

                    mediaService = binder.getService();
                    isBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isBound = false;
                }
            };

        @Override
        public void onStart() {
            super.onStart();

            if(playIntent == null) {
                playIntent = new Intent(getApplicationContext(), MediaService.class);
                bindService(playIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                startService(playIntent);
            }
        }

        @Override
        public void onDestroy() {
            stopService(playIntent);
            mediaService = null;
            super.onDestroy();
        }

        public void HandlePreviousButtonClick(){

            Log.i(TAG, "previous button clicked");

            Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show();

            mediaService.previousSong();
        }



        public void HandlePlayButtonClick(){
            //show string in logcat
            Log.i(TAG, "play button clicked");

            Toast.makeText(getApplicationContext(), "Play/Pause", Toast.LENGTH_SHORT).show();

            mediaService.playSong();

        }


        public void HandleStopButtonClick(){
            Log.i(TAG, "stop button clicked");
            //stop the song

            Toast.makeText(getApplicationContext(), "Stop", Toast.LENGTH_SHORT).show();

            mediaService.stopSong();

        }



        public void HandleNextButtonClick(){

            Log.i(TAG, "next button clicked");

            Toast.makeText(getApplicationContext(), "Forward", Toast.LENGTH_SHORT).show();

            mediaService.nextSong();
        }

        public void toggleShuffle() {
            mediaService.setShuffle(cbShuffle.isChecked());
        }

        public void toggleLoop() {
            mediaService.setLoop((cbLoop.isChecked()));
        }
    }












    private void playAudio() {
        MediaPlayer player = MediaPlayer.create(this, R.raw.lounge);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.pause();
                mp.release();
            }
        });
    }





    private void stopAudio() {
        MediaPlayer player = MediaPlayer.create(this, R.raw.thirteenthirtyfive);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.pause();
                mp.release();
            }
        });
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

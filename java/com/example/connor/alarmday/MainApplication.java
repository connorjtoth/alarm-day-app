package com.example.connor.alarmday;

/*
 * File: MainApplication.java
 * Author: Connor J. Toth
 * Date: December 2016
 * Version: 1
 */

/* Dependencies */
import android.app.Application;
import android.media.MediaPlayer;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /* Plays music at the very opening of the application then never again */
        try {
            MediaPlayer player = MediaPlayer.create(this, R.raw.alarmdaycry);
            player.start();
        }
        catch (Exception e) { new ErrorDialog(this, e); }
    }
}

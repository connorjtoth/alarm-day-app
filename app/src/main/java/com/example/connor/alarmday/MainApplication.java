package com.example.connor.alarmday;

/*
 * File: MainApplication.java
 * Author: Connor J. Toth
 * Date: December 2016
 * Version: 1
 *
 * Updated 08/31/2026 to compile in Android Studio
 */

/* Dependencies */
import android.app.Application;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatDelegate;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /* Forces Light Mode to enable correct color display with minimal other changes */
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        /* Plays music at the very opening of the application then never again */
        try {
            MediaPlayer player = MediaPlayer.create(this, R.raw.alarmdaycry);
            player.start();
        }
        catch (Exception e) { new ErrorDialog(this, e); }
    }
}

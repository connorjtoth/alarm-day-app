package com.example.connor.alarmday;

/*
 * File: MainActivity.java
 * Author: Connor J. Toth
 * Date: December 2016
 * Version: 1
 */

/* Dependencies */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /* set the text to the number of days until the next alarm day */
        TextView numberText = (TextView) findViewById(R.id.numberText);
        String numString = Integer.toString(AlarmDay.daysUntil());
        numberText.setText(numString);
    }
}

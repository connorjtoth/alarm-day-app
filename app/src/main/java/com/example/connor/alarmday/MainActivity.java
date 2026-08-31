package com.example.connor.alarmday;

/*
 * File: MainActivity.java
 * Author: Connor J. Toth
 * Date: December 2016
 * Version: 1
 *
 * Updated 08/31/2026 to compile in Android Studio
 */

/* Dependencies */
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
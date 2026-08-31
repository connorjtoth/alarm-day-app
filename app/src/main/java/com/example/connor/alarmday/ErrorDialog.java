package com.example.connor.alarmday;
/*
 * File: ErrorDialog.java
 * Author: Connor J. Toth
 * Date: December 2016
 * Version: 1
 */

/* Dependencies */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;

/* class ErrorDialog
 * Shows an error message in an Alert Dialog.
 */
class ErrorDialog {

    /* constructor */
    ErrorDialog ( Context context, Exception e ) {
        AlertDialog alert = new AlertDialog.Builder(context).create();
        alert.setTitle("Error!");
        alert.setMessage(e.toString());
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"Okay",new
                DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No need to do anything here
            }
        });

        alert.show();
    }


}

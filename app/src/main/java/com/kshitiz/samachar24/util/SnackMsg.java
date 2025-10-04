package com.kshitiz.samachar24.util;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.kshitiz.samachar24.R;


public class SnackMsg {


    static public void showMsgShort(View rootView, String msg) {
        Snackbar snackbar = Snackbar.make(rootView, "" + msg,
                Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(rootView.getContext(), R.color.accent));
        snackbar.show();

    }

    static public void showMsgLong(View rootView, String msg) {
        Snackbar snackbar = Snackbar.make(rootView, "" + msg,
                Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(rootView.getContext(), R.color.accent));
        snackbar.show();
    }
}

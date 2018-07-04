package com.kshitiz.samachar24.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckInternet {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


  public static   boolean checkWifiConnected(Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectionManager != null;
        NetworkInfo wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiCheck.isConnected()) {
            // Do whatever here
            Toast.makeText(context, "WiFi is Connected", Toast.LENGTH_SHORT).show();
            return true;

        } else {
            Toast.makeText(context, "WiFi is not Connected", Toast.LENGTH_SHORT).show();
            return false;

        }
    }

}

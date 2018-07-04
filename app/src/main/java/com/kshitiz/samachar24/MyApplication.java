package com.kshitiz.samachar24;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

import io.paperdb.Paper;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(getBaseContext());
        //initialize the AdMob App
        MobileAds.initialize(this, getString(R.string.admob_app_id));


    }
}

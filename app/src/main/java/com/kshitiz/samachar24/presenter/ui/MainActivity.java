package com.kshitiz.samachar24.presenter.ui;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.kshitiz.samachar24.R;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Fragment fragment;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_nepali_feeds);



/*
        adView = findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("13CA7DC3FAB7D1E7D0B6EDDBC9ADAA1F")
                .build();
        adView.loadAd(adRequest);
*/

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_nepali_feeds:
                fragment = new NepaliFeedFrag();
                openFragment(fragment);
                return true;
            case R.id.navigation_english_feeds:
                fragment = new EnglishFeedFrag();
                openFragment(fragment);
                return true;
            case R.id.navigation_settings:
                fragment = new ProfileFrag();
                openFragment(fragment);
                return true;

        }
        return false;
    }

    private void openFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
    }

}

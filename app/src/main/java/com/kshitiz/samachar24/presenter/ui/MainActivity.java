package com.kshitiz.samachar24.presenter.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kshitiz.samachar24.R;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_nepali_feeds);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.navigation_english_feeds) {
            fragment = new EnglishFeedFrag();
            openFragment(fragment);
            return true;
        } else if (R.id.navigation_nepali_feeds == item.getItemId()) {
            fragment = new NepaliFeedFrag();
            openFragment(fragment);
            return true;

        } else {
            fragment = new ProfileFrag();
            openFragment(fragment);
            return true;
        }
    }

    private void openFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
    }

}

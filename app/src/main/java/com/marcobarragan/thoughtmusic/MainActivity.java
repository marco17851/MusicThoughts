package com.marcobarragan.thoughtmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcobarragan.thoughtmusic.app.MusicThoughtsApplication;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MusicThoughtsApplication) getApplication()).getAppComponent().inject(this);
    }
}

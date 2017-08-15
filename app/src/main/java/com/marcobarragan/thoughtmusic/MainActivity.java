package com.marcobarragan.thoughtmusic;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcobarragan.thoughtmusic.app.MusicThoughtsApplication;

public class MainActivity extends AppCompatActivity {

    ThoughtMusicPagerAdapter mPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MusicThoughtsApplication) getApplication()).getAppComponent().inject(this);

        mPagerAdapter = new ThoughtMusicPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.categories_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }
}

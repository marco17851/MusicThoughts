package com.marcobarragan.thoughtmusic.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.ThoughtMusicPagerAdapter;
import com.marcobarragan.thoughtmusic.app.ThoughtMusicApplication;
import com.marcobarragan.thoughtmusic.dagger.DaggerAppComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Inject
    ThoughtMusicPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.categories_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }

    protected MainComponent getMainComponent(){
        return ((ThoughtMusicApplication) getApplication())
                .getAppComponent()
                .newMainComponent(new MainModule(this));
    }
}

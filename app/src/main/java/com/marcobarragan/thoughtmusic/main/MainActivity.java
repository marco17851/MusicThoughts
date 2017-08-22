package com.marcobarragan.thoughtmusic.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.ThoughtMusicPagerAdapter;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;
import com.marcobarragan.thoughtmusic.network.NetModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Inject
    ThoughtMusicPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GenreFragment fragment = GenreFragment.newInstance();

        MainComponent mainComponent = getMainComponent(fragment);
        mainComponent.inject(this);
        mainComponent.inject(fragment);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.categories_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private MainComponent getMainComponent(GenreFragment fragment) {
        return DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .genreModule(new GenreModule(fragment))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .build();
    }
}

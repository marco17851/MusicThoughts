package com.marcobarragan.thoughtmusic.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.ThoughtMusicPagerAdapter;
import com.marcobarragan.thoughtmusic.app.ThoughtMusicApplication;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Inject
    ThoughtMusicPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GenreFragment fragment = GenreFragment.newInstance();
        GenreModule genreModule = new GenreModule(fragment);
        ((ThoughtMusicApplication) getApplication())
                .getAppComponent()
                .newMainComponent(new MainModule(this), genreModule)
                .inject(this);
        ((ThoughtMusicApplication) getApplication())
                .getAppComponent()
                .newGenreComponent(genreModule)
                .inject(fragment);
//        DaggerGenreComponent.builder().genreModule(genreModule).build().inject(fragment);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.categories_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }
}

package com.marcobarragan.thoughtmusic.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.ThoughtMusicPagerAdapter;
import com.marcobarragan.thoughtmusic.artist.ArtistFragment;
import com.marcobarragan.thoughtmusic.artist.ArtistModule;
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
        ArtistFragment artistFragment = ArtistFragment.newInstance();
        GenreFragment genreFragment = GenreFragment.newInstance();

        MainComponent mainComponent = getMainComponent(artistFragment, genreFragment);
        mainComponent.inject(this);
        mainComponent.inject(artistFragment);
        mainComponent.inject(genreFragment);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.categories_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private MainComponent getMainComponent(ArtistFragment artistFragment, GenreFragment genreFragment) {
        return DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .genreModule(new GenreModule(genreFragment))
                .artistModule(new ArtistModule(artistFragment))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .build();
    }
}

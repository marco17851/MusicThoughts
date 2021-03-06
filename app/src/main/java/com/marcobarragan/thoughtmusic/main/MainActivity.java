package com.marcobarragan.thoughtmusic.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.album.AlbumFragment;
import com.marcobarragan.thoughtmusic.album.AlbumModule;
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
        AlbumFragment albumFragment = AlbumFragment.newInstance();
        GenreFragment genreFragment = GenreFragment.newInstance();
        artistFragment.setRetainInstance(true);
        albumFragment.setRetainInstance(true);
        genreFragment.setRetainInstance(true);

        MainComponent mainComponent = getMainComponent(artistFragment, albumFragment, genreFragment);
        mainComponent.inject(this);
        mainComponent.inject(artistFragment);
        mainComponent.inject(albumFragment);
        mainComponent.inject(genreFragment);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.categories_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private MainComponent getMainComponent(ArtistFragment artistFragment, AlbumFragment albumFragment, GenreFragment genreFragment) {
        return DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .albumModule(new AlbumModule(albumFragment))
                .genreModule(new GenreModule(genreFragment))
                .artistModule(new ArtistModule(artistFragment))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .build();
    }
}

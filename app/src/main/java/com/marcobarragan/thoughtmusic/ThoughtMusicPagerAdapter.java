package com.marcobarragan.thoughtmusic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.marcobarragan.thoughtmusic.album.AlbumContract;
import com.marcobarragan.thoughtmusic.album.AlbumFragment;
import com.marcobarragan.thoughtmusic.artist.ArtistContract;
import com.marcobarragan.thoughtmusic.artist.ArtistFragment;
import com.marcobarragan.thoughtmusic.genre.GenreContract;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;

import javax.inject.Inject;

public class ThoughtMusicPagerAdapter extends FragmentPagerAdapter{

    private final FragmentManager mManager;
    private final GenreFragment mGenreFragment;
    private final AlbumFragment mAlbumFragment;
    private final ArtistFragment mArtistFragment;
    private int NUM_CATEGORIES = 3;

    @Inject
    public ThoughtMusicPagerAdapter(FragmentManager manager, ArtistContract.View artistFragment, AlbumContract.View albumFragment, GenreContract.View genreFragment) {
        super(manager);
        mManager = manager;
        mGenreFragment = (GenreFragment) genreFragment;
        mAlbumFragment = (AlbumFragment) albumFragment;
        mArtistFragment = (ArtistFragment) artistFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mArtistFragment;
            case 1:
                return mAlbumFragment;
            case 2:
                return mGenreFragment;
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_CATEGORIES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Artists";
            case 1:
                return "Albums";
            case 2:
                return "Genres";
            default:
                return "";
        }
    }
}

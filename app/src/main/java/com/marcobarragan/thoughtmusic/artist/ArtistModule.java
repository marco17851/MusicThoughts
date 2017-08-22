package com.marcobarragan.thoughtmusic.artist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marcobarragan.thoughtmusic.dagger.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistModule {

    private final ArtistContract.View mView;

    public ArtistModule(ArtistContract.View view) {
        mView = view;
    }

    @Provides
    ArtistContract.View getView(){
        return mView;
    }

    @Provides
    ArtistAdapter.ArtistAdapterOnClickHandler providesArtistOnClickHandler(){
        return (ArtistAdapter.ArtistAdapterOnClickHandler) mView;
    }
}

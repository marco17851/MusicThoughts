package com.marcobarragan.thoughtmusic.album;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumModule {

    private final AlbumContract.View mView;

    public AlbumModule(AlbumContract.View view) {
        mView = view;
    }

    @Provides
    AlbumContract.View getView(){
        return mView;
    }

    @Provides
    AlbumAdapter.AlbumAdapterOnClickHandler providesAlbumOnClickHandler(){
        return (AlbumAdapter.AlbumAdapterOnClickHandler) mView;
    }
}

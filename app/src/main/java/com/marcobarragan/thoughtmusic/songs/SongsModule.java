package com.marcobarragan.thoughtmusic.songs;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class SongsModule {

    private final SongsContract.View mView;

    public SongsModule(SongsContract.View view) {
        mView = view;
    }

    @Provides
    SongsContract.View getView(){
        return mView;
    }

    @Provides
    public Context getActivityContext(){
        return (Context) mView;
    }
}

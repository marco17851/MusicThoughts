package com.marcobarragan.thoughtmusic.musicPlayer;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MusicPlayerModule {

    private final MusicPlayerActivity mActivity;

    public MusicPlayerModule(MusicPlayerActivity activity) {
        mActivity = activity;
    }

    @Provides
    public Context getActivityContext(){
        return mActivity;
    }

    @Provides
    public Picasso getPicasso(){
        return Picasso.with(getActivityContext());
    }
}

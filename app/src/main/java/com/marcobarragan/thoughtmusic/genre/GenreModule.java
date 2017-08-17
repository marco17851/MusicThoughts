package com.marcobarragan.thoughtmusic.genre;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marcobarragan.thoughtmusic.dagger.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class GenreModule {
    private final GenreContract.View mView;

    public GenreModule(GenreContract.View view) {
        mView = view;
    }

    @Provides
    GenreContract.View getView(){
        return mView;
    }

    @Provides
    public RecyclerView.LayoutManager providesLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }
}

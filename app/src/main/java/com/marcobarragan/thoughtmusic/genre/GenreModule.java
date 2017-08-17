package com.marcobarragan.thoughtmusic.genre;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.marcobarragan.thoughtmusic.dagger.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class GenreModule {
    private final GenreFragment fragment;

    public GenreModule(GenreFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public GenreFragment providesFragment() {
        return fragment;
    }

    @Provides
    public RecyclerView.LayoutManager providesLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }
}

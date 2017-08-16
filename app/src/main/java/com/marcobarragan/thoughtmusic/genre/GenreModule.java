package com.marcobarragan.thoughtmusic.genre;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    public LinearLayoutManager providesLayoutManager(Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    public RecyclerView.Adapter providesAdapter(GenreAdapter adapter) {
        return adapter;
    }

//    @Provides
//    public RepositoriesListPresenter providesPresenter(SomePresenterImpl presenter) {
//        return presenter;
//    }
}

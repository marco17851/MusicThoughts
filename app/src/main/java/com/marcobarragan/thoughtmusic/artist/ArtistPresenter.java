package com.marcobarragan.thoughtmusic.artist;

import com.marcobarragan.thoughtmusic.models.Artist;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class ArtistPresenter implements ArtistContract.Presenter {

    @Inject
    Retrofit retrofit;

    private ArtistContract.View mView;

    @Inject
    ArtistPresenter(ArtistContract.View view) {
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }
    @Override
    public void start() {

    }
}

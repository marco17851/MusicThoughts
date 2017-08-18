package com.marcobarragan.thoughtmusic.songs;

import javax.inject.Inject;

public class SongsPresenter implements SongsContract.Presenter  {

    private SongsContract.View mView;

    @Inject
    SongsPresenter(SongsContract.View view){
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

package com.marcobarragan.thoughtmusic.songs;

import com.marcobarragan.thoughtmusic.fakeData.FakeDataRepository;
import com.marcobarragan.thoughtmusic.models.Song;

import org.json.JSONException;

import java.util.List;

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
        List<Song> songs = FakeDataRepository.getSongs();
        if (songs != null){
            mView.setSongs(songs);
        }
    }
}

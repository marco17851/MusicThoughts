package com.marcobarragan.thoughtmusic.genre;

import com.marcobarragan.thoughtmusic.fakeData.FakeDataRepository;
import com.marcobarragan.thoughtmusic.models.Genre;

import org.json.JSONException;

import java.util.List;

import javax.inject.Inject;

public class GenrePresenter implements GenreContract.Presenter {

    private GenreContract.View mView;

    @Inject
    GenrePresenter(GenreContract.View view){
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        try {
            List<Genre> genres = FakeDataRepository.getGenres();
            if (genres != null){
                mView.setGenres(genres);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            mView.showErrorMessage();
        }
    }
}
